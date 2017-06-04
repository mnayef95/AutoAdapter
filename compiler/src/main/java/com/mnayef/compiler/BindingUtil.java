package com.mnayef.compiler;

import com.mnayef.annotations.Link;
import com.squareup.javapoet.MethodSpec;

/**
 * Created by Mohamed Hamdan on 2017-May-29.
 * mohamed.nayef95@gmail.com
 */
public class BindingUtil {

    public static void bindTextView(MethodSpec.Builder onBindViewHolder, String fieldName, String methodName) {
        onBindViewHolder.addStatement("holder.$N.setText($N.get($N).get$N())", fieldName, "list", "position", StringUtil.makeFirstCharToUpper(methodName));
    }

    public static void bindVideoView(MethodSpec.Builder onBindViewHolder, String fieldName, String methodName) {
        onBindViewHolder.addStatement("holder.$N.setVideoURI($T.parse($N.get($N).get$N()))", fieldName, ClassesNames.URI, "list", "position", StringUtil.makeFirstCharToUpper(methodName));
        onBindViewHolder.addStatement("holder.$N.start()", fieldName);
    }

    public static void bindCheck(MethodSpec.Builder onBindViewHolder, String fieldName, String methodName) {
        onBindViewHolder.addStatement("holder.$N.setChecked($N.get($N).is$N())", fieldName, "list", "position", StringUtil.makeFirstCharToUpper(methodName));
    }

    public static void bindVisibilityView(MethodSpec.Builder onBindViewHolder, String fieldName, String methodName) {
        onBindViewHolder.addStatement("holder.$N.setVisibility($N.get($N).is$N() ? $T.VISIBLE : $T.GONE)", fieldName, "list", "position", StringUtil.makeFirstCharToUpper(methodName), ClassesNames.VIEW, ClassesNames.VIEW);
    }

    public static void bindLinkPreview(MethodSpec.Builder onBindViewHolder, Link link, String fieldName, String methodName) {
        if (!link.failedLoadMag().isEmpty()) {
            onBindViewHolder.addStatement("holder.$N.setFailedLoadMsg($S)", fieldName, link.failedLoadMag());
        } else if (link.failedLoadMagRes() != 0) {
            onBindViewHolder.addStatement("holder.$N.setFailedLoadMsg($L)", fieldName, link.failedLoadMagRes());
        }

        if (!link.invalidLinkMag().isEmpty()) {
            onBindViewHolder.addStatement("holder.$N.setInvalidLinkMsg($S)", fieldName, link.invalidLinkMag());
        } else if (link.invalidLinkMagRes() != 0) {
            onBindViewHolder.addStatement("holder.$N.setInvalidLinkMsg($L)", fieldName, link.invalidLinkMagRes());
        }
        onBindViewHolder.addStatement("holder.$N.load($N.get($N).get$N())", fieldName, "list", "position", StringUtil.makeFirstCharToUpper(methodName));
    }
}
