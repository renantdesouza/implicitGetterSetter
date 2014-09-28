package util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Usado para dar ou inibir 
 * o acesso e a alteração 
 * dos atributos das classes
 * via Alterable.
 * 
 * Exemplo de implementação:<br/>
 * {@code @Inacessible(get=true, set=true)} <br/>
 * {@code private int atribute}
 * 
 * @author Renan
 * 
 * @since 26/09/2014
 * 
 * @see Alterable
 * */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Inacessible {
	
	/**
	 * Define o acesso ao atributo, 
	 * se {@code true} nega o acesso
	 * se {@code false} dá acesso. 
	 * 
	 * @return boolean
	 * 
	 * @see Alterable#get
	 * @see Alterable#set
	 * */
	public boolean get() default true;
	
	
	/**
	 * Define se o atributo pode ser,
	 * alterado se {@code true} 
	 * inibe o acesso se {@code false} dá acesso. 
	 * 
	 * @return boolean
	 * 
	 * @see Alterable#get
	 * @see Alterable#set
	 * */
	public boolean set() default true;
	
}
