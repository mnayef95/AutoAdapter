package com.mnayef.compiler;

import com.google.auto.service.AutoService;
import com.mnayef.annotations.Adapter;
import com.mnayef.annotations.Check;
import com.mnayef.annotations.Click;
import com.mnayef.annotations.Image;
import com.mnayef.annotations.Link;
import com.mnayef.annotations.LongClick;
import com.mnayef.annotations.Radio;
import com.mnayef.annotations.Text;
import com.mnayef.annotations.Video;
import com.mnayef.annotations.Visibility;
import com.mnayef.annotations.enums.ImageLibraries;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

/**
 * Created by Mohamed Hamdan on 2017-May-22.
 * mohamed.nayef95@gmail.com
 */
@AutoService(Processor.class)
public final class AdapterProcessor extends AbstractProcessor {

    private final List<Class<? extends Annotation>> SUPPORT_ANNOTATIONS = Arrays.asList(
            Adapter.class,
            Text.class,
            Image.class,
            Video.class,
            Click.class,
            LongClick.class,
            Radio.class,
            Check.class,
            Visibility.class,
            Link.class
    );

    private String adapterName;
    private TypeSpec.Builder viewHolder;
    private Elements elementsUtil;
    private Filer filer;
    private ClassName viewHolderClass;
    private TypeName listOfModels;
    private MethodSpec.Builder onBindViewHolder;
    private Map<String, String> createdFields = new HashMap<>();

    private int layoutId;

    @Override
    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);
        elementsUtil = processingEnv.getElementUtils();
        filer = processingEnv.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        for (Element element : env.getElementsAnnotatedWith(Adapter.class)) {
            createdFields.clear();
            String pkgName = elementsUtil.getPackageOf(element).getQualifiedName().toString();
            adapterName = element.getAnnotation(Adapter.class).adapterName();
            layoutId = element.getAnnotation(Adapter.class).layoutId();
            boolean clickable = element.getAnnotation(Adapter.class).clickable();
            viewHolderClass = ClassesNames.getViewHolder(adapterName);

            TypeName adapter = ParameterizedTypeName.get(ClassesNames.ADAPTER, viewHolderClass);
            listOfModels = ClassesNames.getListClassName(pkgName, element.getSimpleName().toString());

            TypeSpec.Builder classBuilder = CodeGenerateUtils.createClass(adapterName, adapter, clickable, getMethods(pkgName, element.getSimpleName().toString()), getFields());

            Map<Element, Annotation> fields = new HashMap<>();
            for (Element enclosedElement : element.getEnclosedElements()) {
                Text text = enclosedElement.getAnnotation(Text.class);
                if (text != null) fields.put(enclosedElement, text);

                Image image = enclosedElement.getAnnotation(Image.class);
                if (image != null) fields.put(enclosedElement, image);

                Video video = enclosedElement.getAnnotation(Video.class);
                if (video != null) fields.put(enclosedElement, video);

                Click click = enclosedElement.getAnnotation(Click.class);
                if (click != null) fields.put(enclosedElement, click);

                LongClick longClick = enclosedElement.getAnnotation(LongClick.class);
                if (longClick != null) fields.put(enclosedElement, longClick);

                Radio radio = enclosedElement.getAnnotation(Radio.class);
                if (radio != null) fields.put(enclosedElement, radio);

                Check check = enclosedElement.getAnnotation(Check.class);
                if (check != null) fields.put(enclosedElement, check);

                Visibility visibility = enclosedElement.getAnnotation(Visibility.class);
                if (visibility != null) fields.put(enclosedElement, visibility);

                Link link = enclosedElement.getAnnotation(Link.class);
                if (link != null) fields.put(enclosedElement, link);
            }

            onBindViewHolder();
            buildViewHolder(clickable, fields);

            if (classBuilder != null) {
                classBuilder.addMethod(onBindViewHolder.build());
                classBuilder.addType(viewHolder.build());
                JavaFile javaFile = JavaFile.builder(pkgName, classBuilder.build()).build();
                try {
                    javaFile.writeTo(filer);
                } catch (IOException ignored) {
                }
            }
        }
        return true;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        for (Class<? extends Annotation> annotation : SUPPORT_ANNOTATIONS) {
            types.add(annotation.getCanonicalName());
        }
        return types;
    }

    private MethodSpec[] getMethods(String pkg, String objectName) {
        return new MethodSpec[]{
                CodeGenerateUtils.getAdapterConstructor(listOfModels),
                CodeGenerateUtils.getOnCreateViewHolder(layoutId, viewHolderClass),
                CodeGenerateUtils.getGetItemCount(),
                CodeGenerateUtils.getList(pkg, objectName)
        };
    }

    private FieldSpec[] getFields() {
        return new FieldSpec[]{
                FieldSpec.builder(listOfModels, "list", Modifier.PRIVATE).build(),
                FieldSpec.builder(ClassesNames.CONTEXT, "context", Modifier.PRIVATE).build()
        };
    }

    private void buildViewHolder(boolean clickable, Map<Element, Annotation> fields) {
        MethodSpec.Builder builder = MethodSpec.constructorBuilder();
        builder.addParameter(ClassesNames.VIEW, "itemView");
        builder.addModifiers(Modifier.PUBLIC);
        builder.addStatement("super(itemView)");
        if (clickable)
            builder.addStatement("itemView.setOnClickListener(new $T(){" +
                    "\n@Override\n" +
                    "public void onClick($T view) {\n" +
                    "if ($N != null)\n" +
                    "$N.OnItemClick($N, $N, $N);\n" +
                    "}\n" +
                    "})", ClassesNames.ON_CLICK, ClassesNames.VIEW, "onItemClickListener", "onItemClickListener", "view", "getAdapterPosition()", "ViewHolder" + ".this");

        viewHolder = TypeSpec.classBuilder("ViewHolder");
        viewHolder.addModifiers(Modifier.PUBLIC);
        viewHolder.superclass(ClassesNames.RECYCLER_HOLDER);

        for (Map.Entry<Element, Annotation> entry : fields.entrySet()) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, entry.toString());
            findViewById(builder, entry.getKey(), entry.getValue());
        }
        viewHolder.addMethod(builder.build());
    }

    private void onBindViewHolder() {
        onBindViewHolder = MethodSpec.methodBuilder("onBindViewHolder")
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(viewHolderClass, "holder", Modifier.FINAL)
                .addParameter(int.class, "position");
    }

    private void findViewById(MethodSpec.Builder builder, Element element, Annotation baseField) {
        FieldSpec fieldSpec;
        processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, baseField + "");
        if (baseField instanceof Text) {
            Text field = (Text) baseField;
            fieldSpec = FieldSpec.builder(ClassesNames.TEXT_VIEW, element.getSimpleName().toString(), Modifier.PRIVATE).build();
            if (createdFields.get(String.valueOf(field.value())) == null) {
                FindViewUtil.findTextView(builder, fieldSpec.name, field);
                viewHolder.addField(fieldSpec);
                createdFields.put(String.valueOf(field.value()), fieldSpec.name);
                BindingUtil.bindTextView(onBindViewHolder, fieldSpec.name, fieldSpec.name);
            } else {
                BindingUtil.bindTextView(onBindViewHolder, createdFields.get(String.valueOf(field.value())), fieldSpec.name);
            }
        } else if (baseField instanceof Image) {
            Image field = (Image) baseField;
            fieldSpec = FieldSpec.builder((field.library() == ImageLibraries.FRESCO ? ClassesNames.SIMPLE_DRAWEE_VIEW : ClassesNames.IMAGE_VIEW), element.getSimpleName().toString(), Modifier.PRIVATE).build();
            if (createdFields.get(String.valueOf(field.value())) == null) {
                FindViewUtil.findImageView(builder, element.getSimpleName().toString(), field);
                viewHolder.addField(fieldSpec);
                createdFields.put(String.valueOf(field.value()), fieldSpec.name);
                bindImageView(builder, fieldSpec.name, field, fieldSpec.name);
            } else {
                bindImageView(builder, createdFields.get(String.valueOf(field.value())), field, fieldSpec.name);
            }
        } else if (baseField instanceof Video) {
            Video field = (Video) baseField;
            fieldSpec = FieldSpec.builder(ClassesNames.VIDEO_VIEW, element.getSimpleName().toString(), Modifier.PRIVATE).build();
            if (createdFields.get(String.valueOf(field.value())) == null) {
                FindViewUtil.findVideoView(builder, element.getSimpleName().toString(), field);
                viewHolder.addField(fieldSpec);
                createdFields.put(String.valueOf(field.value()), fieldSpec.name);
                BindingUtil.bindVideoView(onBindViewHolder, fieldSpec.name, fieldSpec.name);
            } else {
                BindingUtil.bindVideoView(onBindViewHolder, createdFields.get(String.valueOf(field.value())), fieldSpec.name);
            }
        } else if (baseField instanceof Click) {
            Click field = (Click) baseField;
            String fieldName = createdFields.get(String.valueOf(field.value()));
            onClick(builder, element.getSimpleName().toString(), field, fieldName, fieldName == null);
        } else if (baseField instanceof LongClick) {
            LongClick field = (LongClick) baseField;
            String fieldName = createdFields.get(String.valueOf(field.value()));
            onLongClick(builder, element.getSimpleName().toString(), field, fieldName, fieldName == null);
        } else if (baseField instanceof Check) {
            Check field = (Check) baseField;
            fieldSpec = FieldSpec.builder(ClassesNames.CHECK_BOX, element.getSimpleName().toString(), Modifier.PRIVATE).build();
            if (createdFields.get(String.valueOf(field.value())) == null) {
                FindViewUtil.findCheckBox(builder, element.getSimpleName().toString(), field);
                viewHolder.addField(fieldSpec);
                createdFields.put(String.valueOf(field.value()), fieldSpec.name);
                BindingUtil.bindCheck(onBindViewHolder, fieldSpec.name, fieldSpec.name);
            } else {
                BindingUtil.bindCheck(onBindViewHolder, createdFields.get(String.valueOf(field.value())), fieldSpec.name);
            }
        } else if (baseField instanceof Radio) {
            Radio field = (Radio) baseField;
            fieldSpec = FieldSpec.builder(ClassesNames.RADIO_BUTTON, element.getSimpleName().toString(), Modifier.PRIVATE).build();
            if (createdFields.get(String.valueOf(field.value())) == null) {
                FindViewUtil.findRadioButton(builder, element.getSimpleName().toString(), field);
                viewHolder.addField(fieldSpec);
                createdFields.put(String.valueOf(field.value()), fieldSpec.name);
                BindingUtil.bindCheck(onBindViewHolder, fieldSpec.name, fieldSpec.name);
            } else {
                BindingUtil.bindCheck(onBindViewHolder, createdFields.get(String.valueOf(field.value())), fieldSpec.name);
            }
        } else if (baseField instanceof Visibility) {
            Visibility field = (Visibility) baseField;
            fieldSpec = FieldSpec.builder(ClassesNames.VIEW, element.getSimpleName().toString(), Modifier.PRIVATE).build();
            if (createdFields.get(String.valueOf(field.value())) == null) {
                FindViewUtil.findVisibilityView(builder, element.getSimpleName().toString(), field);
                viewHolder.addField(fieldSpec);
                createdFields.put(String.valueOf(field.value()), fieldSpec.name);
                BindingUtil.bindVisibilityView(onBindViewHolder, fieldSpec.name, fieldSpec.name);
            } else {
                BindingUtil.bindVisibilityView(onBindViewHolder, createdFields.get(String.valueOf(field.value())), fieldSpec.name);
            }
        } else if (baseField instanceof Link) {
            Link field = (Link) baseField;
            fieldSpec = FieldSpec.builder(ClassesNames.LINK_PREVIEW, element.getSimpleName().toString(), Modifier.PRIVATE).build();
            if (createdFields.get(String.valueOf(field.value())) == null) {
                FindViewUtil.findLinkView(builder, element.getSimpleName().toString(), field);
                viewHolder.addField(fieldSpec);
                createdFields.put(String.valueOf(field.value()), fieldSpec.name);
                BindingUtil.bindLinkPreview(onBindViewHolder, field, fieldSpec.name, fieldSpec.name);
            } else {
                BindingUtil.bindVisibilityView(onBindViewHolder, createdFields.get(String.valueOf(field.value())), fieldSpec.name);
            }
        }
    }

    private void onClick(MethodSpec.Builder builder, String methodName, Click click, String fieldName, boolean find) {
        if (find) {
            builder.addStatement("\nitemView.findViewById($L).setOnClickListener(new $T(){" +
                    "\n@Override\n" +
                    "public void onClick($T view) {\n" +
                    "   int position = getAdapterPosition();\n" +
                    "   if (position != RecyclerView.NO_POSITION){\n" +
                    "       $N.get(position).$N($N, $N, $N);\n" +
                    "   }\n" +
                    "}\n" +
                    "})", click.value(), ClassesNames.ON_CLICK, ClassesNames.VIEW, "list", methodName, adapterName + ".this", "position", "view");
        } else {
            builder.addStatement("\n$N.setOnClickListener(new $T(){" +
                    "\n@Override\n" +
                    "public void onClick($T view) {\n" +
                    "   int position = getAdapterPosition();\n" +
                    "   if (position != RecyclerView.NO_POSITION){\n" +
                    "       $N.get(position).$N($N, $N, $N);\n" +
                    "   }\n" +
                    "}\n" +
                    "})", fieldName, ClassesNames.ON_CLICK, ClassesNames.VIEW, "list", methodName, adapterName + ".this", "position", "view");
        }
    }

    private void onLongClick(MethodSpec.Builder builder, String methodName, LongClick click, String fieldName, boolean find) {
        if (find) {
            builder.addStatement("\nitemView.findViewById($L).setOnLongClickListener(new $T(){" +
                    "\n@Override\n" +
                    "public boolean onLongClick($T view) {\n" +
                    "   int position = getAdapterPosition();\n" +
                    "   if (position != RecyclerView.NO_POSITION){\n" +
                    "       $N.get(position).$N($N, $N, $N);\n" +
                    "   }\n" +
                    "return false;\n" +
                    "}\n" +
                    "})", click.value(), ClassesNames.ON_LONG_CLICK, ClassesNames.VIEW, "list", methodName, adapterName + ".this", "position", "view");
        } else {
            builder.addStatement("\n$N.setOnClickLongListener(new $T(){" +
                    "\n@Override\n" +
                    "public boolean onLongClick($T view) {\n" +
                    "   int position = getAdapterPosition();\n" +
                    "   if (position != RecyclerView.NO_POSITION){\n" +
                    "       $N.get(position).$N($N, $N, $N);\n" +
                    "   }\n" +
                    "return false;\n" +
                    "}\n" +
                    "})", fieldName, ClassesNames.ON_LONG_CLICK, ClassesNames.VIEW, "list", methodName, adapterName + ".this", "position", "view");
        }
    }

    private void bindImageView(MethodSpec.Builder builder, String fieldName, Image image, String methodName) {
        String getMethodName = "list.get(position).get" + StringUtil.makeFirstCharToUpper(methodName) + "()";
        switch (image.library()) {
            case FRESCO: {
                FrescoUtil.bindFresco(viewHolder, onBindViewHolder, builder, getMethodName, image, fieldName);
                break;
            }
            case PICASSO: {
                PicassoUtil.bindPicasso(onBindViewHolder, viewHolder, builder, getMethodName, image, fieldName);
                break;
            }
        }
    }
}
