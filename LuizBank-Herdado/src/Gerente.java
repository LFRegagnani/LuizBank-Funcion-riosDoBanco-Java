public class Gerente extends Funcionario implements Autenticavel{

    private AutenticacaoCodigo autentica;



    Gerente(){
        this.autentica = new AutenticacaoCodigo();
    }


    @Override
    public double getBonifica(){
        return super.getSalario();
    }


    public boolean autentica(int senha) {
        return this.autentica.autentica(senha);
    }


    public void setSenha(int senha) {
        this.autentica.setSenha(senha);
    }
}