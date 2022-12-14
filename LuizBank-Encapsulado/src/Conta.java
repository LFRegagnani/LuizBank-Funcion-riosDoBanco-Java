public class Conta {
    //dados da conta
    private double saldo;
    private int agencia;
    private int numeroConta;
    private double limiteDaConta = 100;
    private double limiteTotal = limiteDaConta;
    private double total = this.saldo + this.limiteDaConta;
    private Cliente titular;  //referencia a um objeto cliente
    private static int totalDeContas;


    Conta(int agencia, int numeroConta) {//construtor, existe para definir o padrão da classe quando implementada
        setAgencia(agencia);
        setNumeroConta(numeroConta);
        Conta.totalDeContas++;
        System.out.println("A conta número " + this.numeroConta +
                " da agencia " + this.agencia + " foi criada com sucesso!" +
                " Agora o total de contas criadas é " + Conta.totalDeContas);


    }


    //metodo usado para depositar valores em saldo
    public void deposita(double valor) {
        this.saldo += valor;
        this.total = this.saldo + this.limiteDaConta;

        if (this.saldo >= 0) {//faz o limite voltar ao padrão da conta quando o cliente quita o negativo
            this.limiteDaConta = this.limiteTotal;
        }
    }


    public boolean saca(double valor) {//metodo de sacar valores de saldo ou de limite se n tiver saldo
        //esta sequencia de if faz com que o cliente use o limite se o saldo seja insuficiente
        if (this.total >= valor) {
            if (this.saldo >= valor) {//aqui é quando ele tem saldo suficiente
                this.saldo -= valor;
                this.total = this.saldo + this.limiteDaConta;
            } else {//aqui é quando ele não tem saldo suficiente mas tem limite para cobrir a operação
                this.saldo -= valor;
                this.limiteDaConta += this.saldo;
                this.total = this.limiteDaConta;
            }
            return true;

        } else {
            this.total = this.saldo + this.limiteDaConta;
            System.out.println("Você não pode sacar este valor, sua conta tem R$ " + this.saldo +
                    " e o seu limite atual é R$ " + this.limiteDaConta);
            return false;
        }
    }


    public boolean transfere(double valor, Conta destino) { //metodo para transferir valores de uma conta para outra

        if (this.total >= valor) {
            this.saca(valor);
            destino.deposita(valor);

            return true;
        } else {
            System.out.println("Você não pode transferir este valor, sua conta tem R$ " + this.saldo +
                    " e o seu limite atual é R$ " + this.limiteDaConta);
            return false;
        }


    }

    //GETTs e SETTs
    public double getSaldo() { // metodo que mostra o saldo do cliente já que o saldo é privado
        return this.saldo;
    }

    public int getNumeroConta() {//mostra o numero da conta como uma int
        return this.numeroConta;
    }

    public void setNumeroConta(int numero) {//altera o numero da conta sendo o novo numero como argumento
        if (numero <= 0) {
            System.out.println("Esse não é um valor valido");
            return;
        }

        this.numeroConta = numero;
    }

    public int getAgencia() {// mostra o numero da agencia como uma int
        return this.agencia;
    }

    public void setAgencia(int agencia) {//altera o numero da agencia tendo uma int como argumento
        if (agencia <= 0) {
            System.out.println("Esse não é um valor valido");
            return;
        }
        this.agencia = agencia;
    }

    public Cliente getTitular() {// mostrar o titular da conta
        return this.titular;
    }

    public void setTitular(Cliente titular) {//alterar o cliente titular da conta
        this.titular = titular;
    }

    public double getLimiteDaConta() {//mostrar o limite da conta
        return this.limiteDaConta;
    }

    public void setLimiteDaConta(double novoLimite) {//alterar o limite atual e o total da conta
        this.limiteDaConta = novoLimite;
        this.total = this.saldo + this.limiteDaConta;
        this.limiteTotal = limiteDaConta;
    }

    public static int getTotalDeContas() {   // metodo para consultar quantas contas existem
        return Conta.totalDeContas;
    }


}

