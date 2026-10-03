import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EquipController equipController = new EquipController();
        equipController.cargarEquiposIniciales();
        
        int opcion = 0;
        
        while (true) {

            System.out.println("************************************");
            System.out.println("*                                  *");
            System.out.println("*  ¡Bienvenido a nuestro sistema!  *");
            System.out.println("*                                  *");
            System.out.println("************************************");
    
            System.out.println("Elija una opcion por favor:");
            System.out.println("1) Registrar equipos");
            System.out.println("2) Consultar inventario");
            System.out.println("3) Cotizar");
            System.out.println("4) Confirmar alquileres");
            System.out.println("5) Confirmar devolucion");
            System.out.println("6) Salir");
            
            System.out.println("Seleccione una categoría:");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debes ingresar un numero entero:");
                continue;
            }

            switch (opcion) {
                case 1:
                    equipController.registrar();
                break;
                case 2:
                    equipController.mostrarDisponibles();
                break;
                case 3:
                    
                break;
                case 4:
                    equipController.rentar();
                break;
                case 5:
                    equipController.devolver();
                break;
                case 6:
                    System.out.println("¡Gracias por utilizar el sistema!");
                    scanner.close();
                return;
            
                default:
                    System.out.println("Seleccione una opción del 1 al 6.");
                    break;
            }
        }
    }
}