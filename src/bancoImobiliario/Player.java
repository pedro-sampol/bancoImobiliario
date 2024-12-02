package bancoImobiliario;

public class Player {
	private int posicao;
	private int dinheiro;
	private Boolean preso;
	private int tempoPrisao;
	private int sairPrisao;
	private int tempSemJogar;
	private int dadosRepetidos;
	private Spritesheet sprite;
	
	public Player(Spritesheet sprite) {
			this.posicao = 0;
			this.dinheiro = 200;
			this.preso = Boolean.valueOf(false);
			this.tempoPrisao =  0;
			this.sairPrisao = 0;
			this.dadosRepetidos = 0;
			this.sprite = sprite;
		}
	
		//funcoes que retornam informações do estado atual de uma variavel do player
		public int obtemPosicao() {
			return this.posicao;
		}
		public int obtemDinheiro() {
			return this.dinheiro;
		}
		public Boolean estaPreso() {
			return this.preso;
		}
		public int obtemTempoPrisao() {
			return this.tempoPrisao;
		}
		public int numSairPrisao() {
			return this.sairPrisao;
		}
		public int obtemTempoSemJogar() {
			return this.tempSemJogar;
		}	
		public Spritesheet obtemSprite() {
			return this.sprite;
		}
		
		// verifica se o player tem dinheiro suficiente para uma compra
		// retorna true se sim, false se não
		public Boolean temDinheiro(int valor) {
				if(this.dinheiro >= valor)
					return Boolean.valueOf(true);
				else
					return Boolean.valueOf(false);
		}
		
		// altera a quantidade de dinheiro que o player possui
		public void alteraDinheiro(int valor) {
				this.dinheiro += valor;
		}
		
		//move o player num casas para frente
		//retorna true se o player passou ou parou na casa inicial, false se nao
		public Boolean move(int num) {
			Boolean retorno;
			
			//player passou ou parou no inicio
			if(this.posicao + num >= 36)
				retorno = Boolean.valueOf(true);
			else
				retorno = Boolean.valueOf(false);	
				
			//atualiza posicao do player (tabuleiro é circular com 36 casas)
			this.posicao = (this.posicao + num) % 36;
			return retorno;
		}
		
		//player recebe uma carta de fuga da prisao
		public void aumentaCartaFuga() {
			this.sairPrisao++;
		}
		
		//player saiu da prisao
		//se carta == true -> remove uma carta de fuga da prisao
		public void fugirPrisao(Boolean carta) {
			if(carta.equals(true))
				this.sairPrisao--;
			
			this.preso = Boolean.valueOf(false);
		}
		
		//caso os dados sejam repetidos, aumenta a contagem de dados
		//retorna true se foram jogados dados repetidos 3 vezes seguidas em um turno
		public Boolean repeticao(Dice dado) {
			if(dado.isEqual) {
				if(this.dadosRepetidos == 2) {
					this.resetaDadosRepetidos();
					return true;
				}
				this.dadosRepetidos++;
			}
			return false;
		}
		
		//reseta o num de dados repetidos (usado no fim de um turno)
		public void resetaDadosRepetidos() {
			this.dadosRepetidos = 0;
		}
}
