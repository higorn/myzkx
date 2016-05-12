package br.com.sisnunes.myzkx.ui.annotation;

/**
 * Created by higor on 25/03/15.
 */
@java.lang.annotation.Target(java.lang.annotation.ElementType.FIELD)
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface FieldProps
{
	String name() default "";
	Class<?> type() default Object.class;
	String constraint() default "";
	String errortag() default "";
	String fieldtype() default "";
	String format() default "";
	String width() default "";
	int rows() default 1;
	String sclass() default "";
	String placeholder() default "";
	String faicon() default "";
}
