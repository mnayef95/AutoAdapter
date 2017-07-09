package com.mnayef.compiler;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;

/**
 * Created by Mohamed Hamdan on 2017-May-22.
 * mohamed.nayef95@gmail.com
 */
class CodeGenerateUtils {

    static TypeSpec.Builder createClass(String className, TypeName superClass, boolean clickable, MethodSpec[] methods, FieldSpec[] fields) {
        TypeSpec.Builder classBuilder = TypeSpec.classBuilder(className);
        classBuilder.addModifiers(Modifier.PUBLIC);
        classBuilder.superclass(superClass);
        if (clickable) {
            classBuilder.addField(ClassesNames.getOnItemClickListener(className), "onItemClickListener", Modifier.PRIVATE);
            classBuilder.addMethod(getSetOnItemClickListener(className));
            classBuilder.addType(itemClickListener(className));
        }
        for (FieldSpec field : fields) {
            classBuilder.addField(field);
        }
        for (MethodSpec method : methods) {
            classBuilder.addMethod(method);
        }
        return classBuilder;
    }

    static MethodSpec getOnCreateViewHolder(int layoutId, ClassName viewHolderClass) {
        return MethodSpec.methodBuilder("onCreateViewHolder")
                .addAnnotation(Override.class)
                .addStatement("this.context = parent.getContext()")
                .addStatement("$T view = $T.from(parent.getContext()).inflate(" + layoutId + ", parent, false)", ClassesNames.VIEW, ClassesNames.LAYOUT_INFLATER)
                .addStatement("return new $T(view)", viewHolderClass)
                .returns(viewHolderClass)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(ClassesNames.VIEW_GROUP, "parent")
                .addParameter(int.class, "viewType").build();
    }

    static MethodSpec getGetItemCount() {
        return MethodSpec.methodBuilder("getItemCount")
                .addAnnotation(Override.class)
                .addStatement("return list.size()")
                .returns(int.class)
                .addModifiers(Modifier.PUBLIC)
                .build();
    }

    static MethodSpec getAdapterConstructor(TypeName listOfModels) {
        return MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(listOfModels, "list")
                .addCode("this.list = list;\n")
                .build();
    }

    static MethodSpec getList(String pkg, String objectName) {
        return MethodSpec.methodBuilder("getList")
                .addStatement("return list")
                .returns(ClassesNames.getListClassName(pkg, objectName))
                .addModifiers(Modifier.PUBLIC)
                .build();
    }

    private static TypeSpec itemClickListener(String adapterName) {
        return TypeSpec.interfaceBuilder("OnItemClickListener")
                .addModifiers(Modifier.PUBLIC)
                .addMethod(onItemClick(adapterName))
                .build();
    }

    private static MethodSpec onItemClick(String adapterName) {
        return MethodSpec.methodBuilder("OnItemClick")
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                .addParameter(ClassesNames.VIEW, "view")
                .addParameter(int.class, "position")
                .addParameter(ClassesNames.getViewHolder(adapterName), "holder")
                .build();
    }

    private static MethodSpec getSetOnItemClickListener(String adapterName) {
        return MethodSpec.methodBuilder("setOnItemClickListener")
                .addModifiers(Modifier.PUBLIC)
                .addParameter(ClassesNames.getOnItemClickListener(adapterName), "onItemClickListener")
                .addStatement("this.onItemClickListener = onItemClickListener")
                .build();
    }

}
