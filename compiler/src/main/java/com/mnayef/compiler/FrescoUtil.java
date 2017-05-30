package com.mnayef.compiler;


import com.mnayef.annotations.Image;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

/**
 * Created by Mohamed Hamdan on 2017-May-29.
 * mohamed.nayef95@gmail.com
 */
public class FrescoUtil {

    public static void bindFresco(TypeSpec.Builder viewHolder, MethodSpec.Builder onBindViewHolder, MethodSpec.Builder builder, String getMethodName, Image field, String fieldName) {
        if (field.progressId() != -1) {
            onBindViewHolder.addStatement("holder.$N.setVisibility($T.VISIBLE)", fieldName + "Progress", ClassesNames.VIEW);
            FindViewUtil.findProgressBar(viewHolder, builder, fieldName, field);
        }
        switch (field.source()) {
            case FILE: {
                FrescoUtil.fromFile(onBindViewHolder, fieldName, getMethodName, field.progressId() != -1, field.gif());
                break;
            }
            case URL: {
                FrescoUtil.fromUrl(onBindViewHolder, fieldName, getMethodName, field.progressId() != -1, field.gif());
                break;
            }
            case RESOURCES: {
                FrescoUtil.fromResource(onBindViewHolder, fieldName, getMethodName, field.progressId() != -1, field.gif());
                break;
            }
        }
    }

    public static void fromFile(MethodSpec.Builder onBindViewHolder, String fieldName, String getMethodName, boolean progress, boolean gif) {
        String statement = "holder.$N.setController(" +
                "$T.newDraweeControllerBuilder()\n" +
                ".setUri($T.fromFile(new $T($N)))\n";
        if (progress)
            statement += ".setControllerListener(new $T() {\n" +
                    "            @Override\n" +
                    "            public void onFinalImageSet(String id, $T imageInfo, $T anim) {\n" +
                    "                holder.$N.setVisibility($T.GONE);" +
                    "            }\n" +
                    "\n" +
                    "            @Override\n" +
                    "            public void onIntermediateImageSet(String id, $T imageInfo) {\n" +
                    "            }\n" +
                    "\n" +
                    "            @Override\n" +
                    "            public void onFailure(String id, $T throwable) {\n" +
                    "            }\n" +
                    "        })\n";
        if (gif) statement += ".setAutoPlayAnimations(true)\n";
        statement += ".build())";

        if (progress) {
            Object[] objects = {fieldName, ClassesNames.FRESCO, ClassesNames.URI, ClassesNames.FILE, getMethodName,
                    ClassesNames.getBaseControllerListener(), ClassesNames.IMAGE_INFO, ClassesNames.ANIMATABLE,
                    fieldName + "Progress", ClassesNames.VIEW, ClassesNames.IMAGE_INFO, ClassesNames.THROWABLE};
            onBindViewHolder.addStatement(statement, objects);
        } else {
            Object[] objects = {fieldName, ClassesNames.FRESCO, ClassesNames.URI, ClassesNames.FILE, getMethodName};
            onBindViewHolder.addStatement(statement, objects);
        }
    }

    public static void fromResource(MethodSpec.Builder onBindViewHolder, String fieldName, String getMethodName, boolean progress, boolean gif) {
        String statement = "holder.$N.setController(\n" +
                "$T.newDraweeControllerBuilder()\n" +
                ".setUri(new $T.$N().scheme($T.LOCAL_RESOURCE_SCHEME).path(String.valueOf($N)).build())\n";
        if (progress)
            statement += ".setControllerListener(new $T() {" +
                    "            @Override\n" +
                    "            public void onFinalImageSet(String id, $T imageInfo, $T anim) {\n" +
                    "                holder.$N.setVisibility($T.GONE);" +
                    "            }\n" +
                    "\n" +
                    "            @Override\n" +
                    "            public void onIntermediateImageSet(String id, $T imageInfo) {\n" +
                    "            }\n" +
                    "\n" +
                    "            @Override\n" +
                    "            public void onFailure(String id, $T throwable) {\n" +
                    "            }\n" +
                    "        })\n";
        if (gif) statement += ".setAutoPlayAnimations(true)\n";
        statement += ".build())";

        if (progress) {
            Object[] objects = {fieldName, ClassesNames.FRESCO, ClassesNames.URI, "Builder", ClassesNames.URI_UTIL, getMethodName,
                    ClassesNames.getBaseControllerListener(), ClassesNames.IMAGE_INFO, ClassesNames.ANIMATABLE,
                    fieldName + "Progress", ClassesNames.VIEW, ClassesNames.IMAGE_INFO, ClassesNames.THROWABLE};
            onBindViewHolder.addStatement(statement, objects);
        } else {
            Object[] objects = {fieldName, ClassesNames.FRESCO, ClassesNames.URI, "Builder", ClassesNames.URI_UTIL, getMethodName};
            onBindViewHolder.addStatement(statement, objects);
        }
    }

    public static void fromUrl(MethodSpec.Builder onBindViewHolder, String fieldName, String getMethodName, boolean progress, boolean gif) {
        String statement = "holder.$N.setController(" +
                "$T.newDraweeControllerBuilder()\n" +
                ".setUri($T.parse($N))\n";
        if (progress)
            statement += ".setControllerListener(new $T() {\n" +
                    "            @Override\n" +
                    "            public void onFinalImageSet(String id, $T imageInfo, $T anim) {\n" +
                    "                holder.$N.setVisibility($T.GONE);" +
                    "            }\n" +
                    "\n" +
                    "            @Override\n" +
                    "            public void onIntermediateImageSet(String id, $T imageInfo) {\n" +
                    "            }\n" +
                    "\n" +
                    "            @Override\n" +
                    "            public void onFailure(String id, $T throwable) {\n" +
                    "            }\n" +
                    "        })\n";
        if (gif) statement += ".setAutoPlayAnimations(true)\n";
        statement += ".build())";

        if (progress) {
            Object[] objects = {fieldName, ClassesNames.FRESCO, ClassesNames.URI, getMethodName,
                    ClassesNames.getBaseControllerListener(), ClassesNames.IMAGE_INFO, ClassesNames.ANIMATABLE,
                    fieldName + "Progress", ClassesNames.VIEW, ClassesNames.IMAGE_INFO, ClassesNames.THROWABLE};
            onBindViewHolder.addStatement(statement, objects);
        } else {
            Object[] objects = {fieldName, ClassesNames.FRESCO, ClassesNames.URI, getMethodName};
            onBindViewHolder.addStatement(statement, objects);
        }
    }

}
