package com.mnayef.compiler;

import com.mnayef.annotations.Check;
import com.mnayef.annotations.Image;
import com.mnayef.annotations.Radio;
import com.mnayef.annotations.Text;
import com.mnayef.annotations.Video;
import com.mnayef.annotations.Visibility;
import com.mnayef.annotations.enums.ImageLibraries;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;

/**
 * Created by Mohamed Hamdan on 2017-May-29.
 * mohamed.nayef95@gmail.com
 */
public class FindViewUtil {

    public static void findProgressBar(TypeSpec.Builder viewHolder, MethodSpec.Builder builder, String fieldName, Image image) {
        viewHolder.addField(FieldSpec.builder(ClassesNames.PROGRESS_BAR, fieldName + "Progress", Modifier.PRIVATE).build());
        builder.addStatement(fieldName + "Progress = ($T) itemView.findViewById($L)", ClassesNames.PROGRESS_BAR, image.progressId());
    }

    public static void findImageView(MethodSpec.Builder builder, String fieldName, Image image) {
        ClassName imageClass = image.library() == ImageLibraries.FRESCO ? ClassesNames.SIMPLE_DRAWEE_VIEW : ClassesNames.IMAGE_VIEW;
        builder.addStatement(fieldName + " = ($T) itemView.findViewById($L)", imageClass, image.value());
    }

    public static void findVideoView(MethodSpec.Builder builder, String fieldName, Video image) {
        builder.addStatement(fieldName + " = ($T) itemView.findViewById($L)", ClassesNames.VIDEO_VIEW, image.value());
    }

    public static void findCheckBox(MethodSpec.Builder builder, String fieldName, Check check) {
        builder.addStatement(fieldName + " = ($T) itemView.findViewById($L)", ClassesNames.CHECK_BOX, check.value());
    }

    public static void findVisibilityView(MethodSpec.Builder builder, String fieldName, Visibility check) {
        builder.addStatement(fieldName + " = itemView.findViewById($L)", check.value());
    }

    public static void findRadioButton(MethodSpec.Builder builder, String fieldName, Radio radio) {
        builder.addStatement(fieldName + " = ($T) itemView.findViewById($L)", ClassesNames.RADIO_BUTTON, radio.value());
    }

    public static void findTextView(MethodSpec.Builder builder, String fieldName, Text text) {
        int viewId = text.value();
        builder.addStatement(fieldName + " = ($T) itemView.findViewById($L)", ClassesNames.TEXT_VIEW, viewId);
    }

}
