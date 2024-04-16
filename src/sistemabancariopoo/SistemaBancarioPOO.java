package sistemabancariopoo;
import java.util.Scanner;
public class SistemaBancarioPOO {
    public static void main(String[] args) {
        Scanner teclado=new Scanner(System.in);
        SISTEMA banco=new SISTEMA();
         //Variables
        boolean MostrarMenuPrincipal=true;  //Mostrar Menu Principal
        boolean HayUsuarios=false;

        
        do{
                 System.out.println("BANCO DE LA CLASE DE PROGRAMACION");
                 System.out.println("             Bienvenido");
                 System.out.println(" ");
                 System.out.println("1) Ingresar con mi cuenta y contraseña");
                 System.out.println("2) Crear cuenta ");
                 System.out.println("3) Salir del sistema ");
                 System.out.println("");
                 System.out.print("ingrese la opcion: ");
                int Opcion= teclado.nextInt();
                //inicio de operaciones 
                switch(Opcion){
                    // ingresar con numero de cuenta y contrase;a 
                    case 1:
                          if (HayUsuarios==false){
                                System.out.println("");
                                System.out.println("No hay usuarios , Ingrese Usuarios para comenzar ");
                                System.out.println("");
                           }else{
                                 System.out.print("ingrese su numero de cuenta: ");
                                 String NCuenta= teclado.next();
                                 System.out.print("ingrese su contraseña: ");
                                 String Contraseña= teclado.next();
                                 //Buscar cuenta en Cuenta
                                 int seencontro=banco.BuscarCuenta(NCuenta,Contraseña);
                                 if (seencontro==-1){
                                    System.out.println("El usuario no existe");
                                     System.out.println("");
                                 }else {
                                    boolean MostrarMenuUsuario=true;
                                    do {
                                        System.out.println("");
                                        System.out.println("Bienvenido " +banco.getClienteN(seencontro));
                                        System.out.println("Su Saldo es de : "+banco.getDinero(seencontro));
                                        System.out.println("----------------------------");
                                        System.out.println("1) Depositar a la cuenta ");
                                        System.out.println("2) Retirar de la cuenta ");
                                        System.out.println("3) Transferir dinero a una cuenta");
                                        System.out.println("4) Ver registro");
                                        System.out.println("5)Salir");
                                        System.out.print("inglese su opcion a elegir: ");
                                        int opcion= teclado.nextInt();
                                        //opciones de menu usuario
                                        switch (opcion){
                                            case 1: 
                                                banco.Depositar(seencontro);
                                            break;
                                            case 2: 
                                                banco.Retirar(seencontro);
                                            break;
                                            case 3: 
                                                banco.Transferir(seencontro);
                                            break;
                                            case 4: 
                                                   banco.Mostrar(seencontro);
                                            break;
                                            case 5: 
                                                  MostrarMenuUsuario=false;
                                            break;

                                            default:
                                                System.out.println("Opcion invalida");
                                            break;     
                                        }
                                    }while (MostrarMenuUsuario==true);

                                }
                                System.out.println("") ;
                           }   
                    break;
                    // Crear cuenta 
                    case 2: 
                        System.out.println("");
                        System.out.println("Creacion de usuario ");
                        System.out.println("----------------------------");
                        System.out.print("Ingrese su nombre: ");
                        String Cnombre= teclado.next();
                        System.out.print("Ingrese contraseña: ");
                        String Ccontraseña= teclado.next();
                        System.out.print("Ingrese el monto inicial: ");
                        double Cmonto =teclado.nextDouble();
                        System.out.print("ingrese la fecha:  ");
                        String  Cfecha =teclado.next();
                        System.out.print("ingrese la hora: ");
                        String  Chora =teclado.next();
                        banco.CrearUsuario(Cnombre, Ccontraseña, Cmonto, Cfecha, Chora);
                        HayUsuarios=true;
                    break;
                    //salir
                    case 3:
                           MostrarMenuPrincipal=false;
                          System.out.println("Usted ha salido");
                     break;

                }
        }while (MostrarMenuPrincipal==true);
        
        
        
    }
}
