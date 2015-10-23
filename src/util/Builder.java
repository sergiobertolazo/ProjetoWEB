 
package  util;

 
public interface Builder<T> {

  // Este é um construtor (Builder) de objetos Model
  // com ele e as classes NomeDaClasseUpate.java e NomeDaClasseCreate.java e
  // LoadersCriam objetos a partir de Wrapper dos dados vindo dos
  // controladores e views.
  T createInstance();

}