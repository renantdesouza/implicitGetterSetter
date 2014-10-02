package util;

public class Pessoa extends Alterable {

	public enum Sexo {
		M("MASCULINO"), F("FEMIMINO");
		
		private String desc;
		
		Sexo(String desc) {
			this.desc = desc;
		}
		 
		public String toString() {
			return desc;
		}
	}
	
	@BeforeGet(method="beforeGet")
	@AfterSet(method="afterSet")
	private String nome;
	private int idade;
	private Sexo sexo;
	
	public void afterSet() {
		System.out.println("executa após setar o nome");
	}
	
	public void beforeGet() {
		System.out.println("executa antes de dar get no nome");
	}
	
	public Pessoa() {
	}
	
	public static void main(String[] args) throws Exception {
		Pessoa p = new Pessoa();
		//O uso do observer, está aqui;
		p.set("nome", "Renan Teixeira de Souza");
		
		// veja o atributo method do @AfterSet
		// ele executa o método afterSet escrito na classe.
		
		p.get("nome");
		
		// agora veja o mesmo atributo na anotacao @BeforeGet
		// ele executa o metodo beforeGet escrito na classe.
		
		//cidinho ta em casa
	}
	
	
}
