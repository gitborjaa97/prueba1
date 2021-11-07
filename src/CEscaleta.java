
import java.util.Scanner;


public class CEscaleta {

    public static void main(String[] args) {
        Scanner woTeclado = new Scanner (System.in);
        
        CPrograma aoEscaleta[] = new CPrograma[24];        
        for (int i = 0; i < 24; i++) {
            aoEscaleta[i]=null;
        }
        while(true){
            System.out.println("1 -> Pelicula 2 ->Variedades 3-> Documental 0->Terminar: ");
            int wiElegir = woTeclado.nextInt();
            if(wiElegir == 0)break;
            if(wiElegir == 1){
                System.out.print("Introduce el nombre: ");
                String wsNombre = woTeclado.next();woTeclado.nextLine();
                System.out.print("Introduce el nombre del director: ");
                String wsNombreDirector = woTeclado.next();woTeclado.nextLine();
                System.out.print("Estreno true/false):" );
                boolean wbEstreno = woTeclado.nextBoolean();woTeclado.nextLine();
                System.out.print("Introduce la hora: ");
                int wiHora = woTeclado.nextInt();woTeclado.nextLine();
                
                CPelicula woPelicula = new CPelicula(wsNombre, wsNombreDirector,wbEstreno );
                if(woPelicula.mProgramar( wiHora,aoEscaleta,woPelicula)){           
                    System.out.println("Se ha realizado la entreda con exito");
                }else System.out.println("No se ha realizado la entrada");
                
                System.out.println("");
            }// if(1)
            if(wiElegir == 2){
                System.out.print("Introduce el nombre: ");
                String wsNombre = woTeclado.next();woTeclado.nextLine();
                System.out.print("Introduce el nombre del presentador: ");
                String wsNombrePres = woTeclado.next();woTeclado.nextLine();
                System.out.print("Estreno true/false):" );
                boolean woEstreno = woTeclado.nextBoolean();woTeclado.nextLine();
                System.out.print("Introduce la duracion: ");
                int wiDuracion = woTeclado.nextInt();woTeclado.nextLine();
                System.out.print("Introduce la hora: ");
                int wiHora = woTeclado.nextInt();woTeclado.nextLine();
                
                CVariedades woVariedades = new CVariedades(wsNombre, wiDuracion, wsNombrePres );
                if(woVariedades.mProgramar( wiHora,aoEscaleta,woVariedades)){           
                    System.out.println("Se ha realizado la entreda con exito");
                }else System.out.println("No se ha realizado la entrada");
                
                System.out.println("");
            }// if(2)
            if(wiElegir == 3){
                System.out.print("Introduce el nombre: ");
                String wsNombre = woTeclado.next();woTeclado.nextLine();
                System.out.print("Introduce el tema: ");
                String wsTema = woTeclado.next();woTeclado.nextLine();
                System.out.print("Redifusion true/false):" );
                boolean wbRedi = woTeclado.nextBoolean();woTeclado.nextLine();
                System.out.print("Introduce la duracion: ");
                int wiDuracion = woTeclado.nextInt();woTeclado.nextLine();
                System.out.print("Introduce la hora: ");
                int wiHora = woTeclado.nextInt();woTeclado.nextLine();
                
                CDocumental woDocumental = new CDocumental(wsNombre, wiDuracion, wsTema, wbRedi );
                if(woDocumental.mProgramar( wiHora,aoEscaleta,woDocumental)){           
                    System.out.println("Se ha realizado la entreda con exito");
                }else System.out.println("No se ha realizado la entrada");
                
                System.out.println("");
            }// if(3)
        }//while(true)
        System.out.println("");
        System.out.println("Escaleta:");
        System.out.println("");
        for (int i = 0; i < aoEscaleta.length; i++) {
            System.out.println(i + "h00 -->" + aoEscaleta[i]);
        }
    }// main()    
}// CEscaleta 

class CPrograma {
    String psNombre;
    int piDuracion;
    
    CPrograma(String isNombre, int iiDuracion){
        psNombre = isNombre; piDuracion =iiDuracion;
    }
    CPrograma(String isNombre){
        psNombre = isNombre;
    }
    public String toString(){
        return("Nombre: "+psNombre+ " Duracion: "+ piDuracion);
    }
    public boolean mProgramar(int iiHora,CPrograma aoEscaleta[] ){
        if(aoEscaleta[iiHora]== null) return true;
        return false;
    }
}// CPrograma

class CPelicula extends CPrograma{
    String psDirector;
    boolean pbEstreno;
    
    CPelicula(String isNombre,String isDirector ,boolean ibEstreno ){
        super(isNombre, 2);
        psDirector = isDirector ; pbEstreno = ibEstreno;
    }
    CPelicula(String isNombre){
        super(isNombre, 2); pbEstreno = true;
    }
    public String toString(){
        return(super.toString()+ " Director: " + psDirector + " Estreno: "+ pbEstreno);
    }
    public boolean mProgramar(int iiHora,CPrograma aoEscaleta[],CPelicula ioPelicula){
        if(pbEstreno && (iiHora == 21 || iiHora == 22)) 
            if(super.mProgramar(iiHora, aoEscaleta) && super.mProgramar((iiHora +1), aoEscaleta)){
                aoEscaleta[iiHora] = ioPelicula;
                aoEscaleta[(iiHora + 1)] = ioPelicula;
                return true;}
        if(!pbEstreno)
            if(super.mProgramar(iiHora, aoEscaleta) && super.mProgramar((iiHora +1), aoEscaleta)){
                aoEscaleta[iiHora] = ioPelicula;
                aoEscaleta[(iiHora + 1)] = ioPelicula;
                return true;
            }
        
        return false;
    }
}// CPelicula
class CVariedades extends CPrograma{
    String psPresentador;
    
    CVariedades(String isNombre,int iiDuracion, String isPresentador){
        super(isNombre, iiDuracion);
        psPresentador = isPresentador;
    }
    public String toString(){
        return(super.toString()+ " Presentador: " + psPresentador );
    }
    public boolean mProgramar(int iiHora,CPrograma aoEscaleta[],CVariedades ioVariedades){
        boolean wbGuardar = false;
        if(iiHora >=18 && (iiHora + (piDuracion-1))<=22){
            int wiHora1 = iiHora;
            for (int i = piDuracion; i > 0; i--) {
                if(aoEscaleta[iiHora] == null)wbGuardar = true ;
                else {wbGuardar = false; break;}
                iiHora++;
            }
            if(wbGuardar){
                for (int i = piDuracion; i > 0; i--){
                    aoEscaleta[wiHora1] =  ioVariedades;
                    wiHora1++;
            }
                return true;
            }                                       
        }        
        return false;
    }
}// CVariedades
class CDocumental extends CPrograma{
    String psTema;
    boolean pbRedifusion;
    
    CDocumental(String isNombre,int iiDuracion,String isTema, boolean ibRedifusion){
        super(isNombre, iiDuracion);
        psTema = isTema; pbRedifusion = ibRedifusion;
    }
    public String toString(){
        return(super.toString()+ " Tema: " + psTema + " Redifusion" + pbRedifusion);
    }
    public boolean mProgramar(int iiHora,CPrograma aoEscaleta[],CDocumental ioDocumental){
        if(psTema.equals("violencia") && (iiHora >=0 && (iiHora + (piDuracion-1))<=5)){
            boolean wbGuardar = false;
            int wiHora1 = iiHora;
            for (int i = piDuracion; i > 0; i--) {
                if(aoEscaleta[iiHora] == null)wbGuardar = true ;
                else {wbGuardar = false; break;}
                iiHora++;
            }
            if(wbGuardar){
                for (int i = piDuracion; i > 0; i--){
                    aoEscaleta[wiHora1] =  ioDocumental;
                    wiHora1++;
            }
                return true;
            }   
        }
        if(!pbRedifusion && (iiHora >=10 && (iiHora+ (piDuracion-1))<=18)){
            boolean wbGuardar = false;
            int wiHora1 = iiHora;
            for (int i = piDuracion; i > 0; i--) {
                if(aoEscaleta[iiHora] == null)wbGuardar = true ;
                else {wbGuardar = false; break;}
                iiHora++;
            }
            if(wbGuardar){
                for (int i = piDuracion; i > 0; i--){
                    aoEscaleta[wiHora1] =  ioDocumental;
                    wiHora1++;
            }
                return true;
            } 
        }
        if(pbRedifusion){
            boolean wbGuardar = false;
            int wiHora1 = iiHora;
            for (int i = piDuracion; i > 0; i--) {
                if(aoEscaleta[iiHora] == null)wbGuardar = true ;
                else {wbGuardar = false; break;}
                iiHora++;
            }
            if(wbGuardar){
                for (int i = piDuracion; i > 0; i--){
                    aoEscaleta[wiHora1] =  ioDocumental;
                    wiHora1++;
            }
                return true;
            }
        }
        return false;
    }
}// CDocumental