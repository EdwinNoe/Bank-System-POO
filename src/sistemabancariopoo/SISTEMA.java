
package sistemabancariopoo;
import java.util.Random;
import java.util.Scanner;
public class SISTEMA {
        Scanner teclado=new Scanner(System.in);
        //atributos
        private String [][] ClienteNCN= new String [5][3]; //nombre contrase;a y numero de cuenta 
        private double [] Dinero = new double [5]; //Solo de 5 cuentas
        private String [][]Registros= new String [20][6]; // Fecha, hora , monto, tipodetrasaccion , numerocuenta y cuenta involucrada . 
        
        //constructor vacio 
        public SISTEMA() {
            
        }
        
        //Publicas
        
        // Seters que sirve para  ingresar datos a ClienteNCN 
        public void CrearUsuario(String Nombre ,String Contraseña, double Monto, String Fecha, String Hora){
            for(int i=0; i< ClienteNCN.length; i++){
                if(ClienteNCN[i][2]==null){//comparare el numero de cuenta 
                    this.ClienteNCN[i][0]=Nombre;
                    this.ClienteNCN[i][1]=Contraseña;
                    this.ClienteNCN[i][2]=CrearNumeroCuenta();
                    this.Dinero[i]=Monto;
                    Registrar(Fecha ,Hora, Monto,"Deposito",this.ClienteNCN[i][2]," " );
                    System.out.println("Su Numero de cuenta es: "+ ClienteNCN[i][2]);
                    break;
                }
            }
        }
        
        
        
        //Metodo Personalizado1
        // Buscarnumero de cuentaS
        public int BuscarCuenta(String NCuenta, String Contraseña) {
            for (int i = 0; i < ClienteNCN.length; i++) {
                if (NCuenta.equals(ClienteNCN[i][2]) && Contraseña.equals(ClienteNCN[i][1])) {
                    return i; 
                }
            }
            return -1; // No se encontró la cuenta
         }
        //Todo lo que se pueda hacer con la cuenta
         public void Depositar(int indice){
            System.out.print("Ingrese la fecha de hoy: ");
            String fecha=teclado.next();
            System.out.print("Ingrese la hora ");
            String hora=teclado.next();
                System.out.print("Ingrese el saldo a depositar  ");
                double despito=teclado.nextDouble();
            Dinero[indice]=Dinero[indice]+despito;

            Registrar(fecha,hora, despito, "Deposito", ClienteNCN[indice][2]," ");
         }
         public void Retirar(int indice){
            System.out.print("Ingrese la fecha de hoy: ");
            String fecha=teclado.next();
            System.out.print("Ingrese la hora ");
            String hora=teclado.next();
            boolean seguir=false;
            do{
                System.out.print("Ingrese el saldo a retirar  ");
                double retiro=teclado.nextDouble();
                if (retiro > Dinero[indice]){
                    System.out.println("no diispone del saldo, intente nuevamente ");
                    System.out.println("");
                }else {
                    Dinero[indice]=Dinero[indice]-retiro;
                    Registrar(fecha,hora, retiro, "Retiro", ClienteNCN[indice][2]," ");
                    seguir=true;
                }
            
            }while(seguir==false);
        }
         public void Transferir(int indice){
            System.out.print("Ingrese la fecha de hoy: ");
            String fecha=teclado.next();
            System.out.print("Ingrese la hora ");
            String hora=teclado.next();
            System.out.print("Ingrese el numero de cuenta a realizar deposito  ");
            String cuenta=teclado.next();
            
            //comenzar una busqueda 
            for (int i=0; i< ClienteNCN.length; i++){
                if (ClienteNCN[i][2]!=null && ClienteNCN[i][2].equals(cuenta)){
                    boolean seguir=false;
                    do {
                        System.out.print("Ingrese el saldo a transferir  ");
                        double transferir=teclado.nextDouble();
                        if(transferir>Dinero[indice]){
                            System.out.println("Cuenta con fondos insufiiciente , vuelva a intentarlo");
                        }else {
                            //hacer el cambiio a las 2 cuentas
                            Dinero[indice]=Dinero[indice]-transferir;
                            Dinero[i]=Dinero[i]+transferir;
                            //regiistrar a las 2 cuentas
                            Registrar(fecha,hora, transferir, "Transferencia", ClienteNCN[indice][2],  cuenta);
                            Registrar(fecha,hora, transferir, "Transferencia", ClienteNCN[i][2],   ClienteNCN[indice][2]);
                            seguir=true;
                        }
                    
                    }while(seguir==false);
                 }
            }
         }
         public void Mostrar (int indice){
             System.out.println("Historial");
            for(int i =0 ;i<Registros.length; i++){
                if (Registros[i][4] != null && Registros[i][4].equals(ClienteNCN[indice][2])){
                    System.out.println("||Fecha: "+Registros[i][0]+"|| Hora De Transaccion: "+Registros[i][1]+"|| Monto De Transaccion: "+Registros[i][2]+"|| Tipo De Transaccion: "+Registros[i][3]+"|| Numero De Cuenta Actual: "+Registros[i][4] +"|| Numero De Cuenta Involucrada: "+Registros[i][5]);
                    System.out.println("");
                }
            }
         }
        // Geters
        public String getClienteN(int Indice) {
            return ClienteNCN [Indice][0];
        }
        public double getDinero(int Indice) {
            return Dinero [Indice];
        }

        //Privada
        //Numero de cuenta 
        private  String CrearNumeroCuenta(){
        Random numeroRandom = new Random();
        char[] caracteres = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','Y','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'};
        char[] cuenta = new char[5];
        
        for (int i = 0; i < 5; i++) {
            int random = numeroRandom.nextInt(37);
            cuenta[i] = caracteres[random];
        }
        
        String numeroCuenta = new String(cuenta); 
        
        return numeroCuenta;
    }
        //Registrar
         private void  Registrar (String Fecha , String Hora, double Monto , String Ttransaccion, String Cuenta, String Cuenta2){
            for(int i=0; i<=Registros.length; i++){
                if(Registros[i][4]==null){ //solo comparamos numero de cuenta 
                    this.Registros[i][0]=Fecha;
                    this.Registros[i][1]=Hora;
                    this.Registros[i][2]=String.valueOf(Monto);
                    this.Registros[i][3]=Ttransaccion;
                    this.Registros[i][4]=Cuenta;
                    this.Registros[i][5]=Cuenta2;
                  break;
                }
        }
        }
        
         
    
        


    
}
