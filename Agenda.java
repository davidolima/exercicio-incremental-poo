import java.util.ArrayList;
import java.util.Calendar;

public class Agenda{
    // assumimos que todas data não bloqueada ou alugada está disponível.
    ArrayList<Calendar> datas_alugadas;
    ArrayList<Calendar> datas_bloqueadas;
    public Agenda(){
        this.datas_alugadas   = new ArrayList<Calendar>();
        this.datas_bloqueadas = new ArrayList<Calendar>();
    }

    public boolean bloquearData(int d, int m, int a){
        Calendar dia_a_bloquear = Calendar.getInstance();
        dia_a_bloquear.set(a,m,d);
        if (!this.datas_bloqueadas.contains(dia_a_bloquear)){
            this.datas_bloqueadas.add(dia_a_bloquear);
            return true;
        }
        return false;
    }
    public void desbloquearData(int d, int m, int a){
        Calendar dia = Calendar.getInstance();
        dia.set(a,m,d);
        this.datas_bloqueadas.remove(dia);
    }

    public boolean alugarData(int d, int m, int a){
        Calendar dia_a_alugar = Calendar.getInstance();
        dia_a_alugar.set(a,m,d);
        if (!this.datas_alugadas.contains(dia_a_alugar)){
            this.datas_alugadas.add(dia_a_alugar);
            return true;
        }
        return false;
    }

    public void disponibilizarData(int d, int m, int a){
        Calendar dia = Calendar.getInstance();
        dia.set(a,m,d);
        if (this.datas_alugadas.contains(dia)){
            this.datas_alugadas.remove(dia);
        }
        if (this.datas_bloqueadas.contains(dia)){
            this.datas_bloqueadas.remove(dia);
        }
    }

    public boolean checarDisponibilidade(int d, int m, int a){
        Calendar dia = Calendar.getInstance();
        dia.set(a,m,d);
        for (int i = 0; i <= Math.max(this.datas_alugadas.size(),this.datas_bloqueadas.size());i++){
            if (i < this.datas_alugadas.size()){
                Calendar da = this.datas_alugadas.get(i);
                if (da.get(Calendar.DATE)  == dia.get(Calendar.DATE)
                    && da.get(Calendar.MONTH) == dia.get(Calendar.MONTH)
                    && da.get(Calendar.YEAR)  == dia.get(Calendar.YEAR)){
                    return false;
                }
            }
            if (i < this.datas_bloqueadas.size()){
                Calendar db = this.datas_bloqueadas.get(i);
                if (db.get(Calendar.DATE)  == dia.get(Calendar.DATE)
                    && db.get(Calendar.MONTH) == dia.get(Calendar.MONTH)
                    && db.get(Calendar.YEAR)  == dia.get(Calendar.YEAR)){
                    return false;
                }
            }
        }
        return true;
    }

    public void verEventos(){
        System.out.println("Datas alugadas:");
        for (Calendar d : this.datas_alugadas){
            System.out.printf(" - %d/%d/%d\n",d.get(Calendar.DATE),d.get(Calendar.MONTH),d.get(Calendar.YEAR));
        }
        System.out.println("Datas bloqueadas:");
        for (Calendar d : this.datas_bloqueadas){
            System.out.printf(" - %d/%d/%d\n",d.get(Calendar.DATE),d.get(Calendar.MONTH),d.get(Calendar.YEAR));System.out.printf(d.toString());
        }
    }
}
