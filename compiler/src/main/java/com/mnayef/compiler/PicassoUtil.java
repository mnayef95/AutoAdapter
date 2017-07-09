package com.mnayef.compiler;

import com.mnayef.annotations.Image;
import com.mnayef.annotations.enums.ImageSource;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

/**
 * Created by Mohamed Hamdan on 2017-May-29.
 * mohamed.nayef95@gmail.com
 */
public class PicassoUtil {

    public static void bindPicasso(MethodSpec.Builder onBindViewHolder, TypeSpec.Builder viewHolder, MethodSpec.Builder builder, String getMethodName, Image field, String fieldName) {
        onBindViewHolder.addStatement("$T $NPicasso = $T.with(context)", ClassesNames.PICASSO, fieldName, ClassesNames.PICASSO);
        if (field.source() == ImageSource.FILE) {
            onBindViewHolder.addStatement("$T $NCreator = $NPicasso.load(new $T($N))", ClassesNames.REQUEST_CREATOR, fieldName, fieldName, ClassesNames.FILE, getMethodName);
        } else {
            onBindViewHolder.addStatement("$T $NCreator = $NPicasso.load($N)", ClassesNames.REQUEST_CREATOR, fieldName, fieldName, getMethodName);
        }
        if (field.resize()) {
            onBindViewHolder.addStatement("$NCreator.resize($L, $L)", fieldName, field.width(), field.height());
        }
        if (field.fit()) {
            onBindViewHolder.addStatement("$NCreator.fit()", fieldName);
        }
        if (field.progressId() != -1) {
            onBindViewHolder.addStatement("holder.$N.setVisibility($T.VISIBLE)", fieldName + "Progress", ClassesNames.VIEW);
            FindViewUtil.findProgressBar(viewHolder, builder, fieldName, field);
            onBindViewHolder.addStatement("$NCreator.into(holder.$N, new $T(){\n@Override\npublic void onSuccess(){\n  holder.$N.setVisibility($T.GONE);\n}\n@Override\npublic void onError(){\n}\n})",
                    fieldName, fieldName, ClassesNames.PICASSO_CALLBACK, fieldName + "Progress", ClassesNames.VIEW);
        } else {
            onBindViewHolder.addStatement("$NCreator.into(holder.$N)", fieldName, fieldName);
        }
    }

}
