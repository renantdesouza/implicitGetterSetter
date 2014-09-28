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
	
	@Inacessible(set=false)
	private String nome;
	@Inacessible(get=false)
	private int idade;
	private Sexo sexo;
	
	
	public Pessoa() {
	}
	
	public static void main(String[] args) throws Exception {
		Pessoa p = new Pessoa();
		
		// Get e Set com acesso total, @Inacessible n�o usado.
		p.set("sexo", Sexo.M);
		System.out.println(p.get("sexo"));
		
		p.set("nome", "Renan");
		//nesse caso gera exce��o get = true na @Inacessible
		//descomente para ver
		//System.out.println(p.get("nome")); 

		//nesse caso gera uma exce��o set = true na @Inacessible
		//descomente para ver
		//p.set("idade", 21); 
		System.out.println(p.get("nome"));
	}
	
	
}
