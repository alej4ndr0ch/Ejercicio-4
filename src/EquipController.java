import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EquipController {
    private final Scanner scanner = new Scanner(System.in);

    private final List<EquipModel> equipos = new ArrayList<>();

    public void registrar() {
        System.out.println("¡Agregue el equipo!");

        String[] categorias = CategoryEquip.getCategories();

        System.out.println("Seleccione una categoría:");
        for (int i = 0; i < categorias.length; i++) {
            System.out.println((i + 1) + ". " + categorias[i]);
        }

        CategoryEquip nuevo;

        while (true) {
            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                nuevo = new CategoryEquip(opcion);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(
                        "Ingrese un número del 1 al " + categorias.length + ":");
            }
        }

        try {
            System.out.println("Ingrese el código único del producto:");
            nuevo.setCode(Integer.parseInt(scanner.nextLine()));

            System.out.println("Ingrese el modelo del producto:");
            nuevo.setModel(scanner.nextLine());

            System.out.println("Ingrese la marca del producto:");
            nuevo.setBrand(scanner.nextLine());

            System.out.println("Ingrese la tarifa por día del producto:");
            nuevo.setFee(Integer.parseInt(scanner.nextLine()));

            System.out.println(
                    "Ingrese las características de: " + nuevo.getCategory());
            nuevo.setCharacteristic(scanner.nextLine());

            System.out.println("Código del equipo que se agregará: " + nuevo.getCode());
            nuevo.validar();

            for (EquipModel equipo : equipos) {
                if (equipo.getCode().equals(nuevo.getCode())) {
                    throw new IllegalArgumentException(
                            "Ya existe un equipo con ese código.");
                }
            }

            equipos.add(nuevo);
            System.out.println("Equipo registrado correctamente.");

        } catch (NumberFormatException e) {
            System.out.println(
                    "No se registró el equipo: código y tarifa deben ser enteros.");
        } catch (IllegalArgumentException e) {
            System.out.println("No se registró el equipo: " + e.getMessage());
        }
    }

    public void mostrarDisponibles() {
        boolean hayDisponibles = false;

        for (EquipModel equipo : equipos) {
            if (equipo.isDisponibility()) {
                hayDisponibles = true;

                System.out.println("------------------------------");
                System.out.println("Código: " + equipo.getCode());
                System.out.println("Modelo: " + equipo.getModel());
                System.out.println("Marca: " + equipo.getBrand());
                System.out.println("Tarifa: " + equipo.getFee());
                System.out.println("Cantidad: " + equipo.getAmount());
                System.out.println("Estado: Disponible");

                if (equipo instanceof CategoryEquip) {
                    CategoryEquip equipoCategoria = (CategoryEquip) equipo;

                    System.out.println(
                            "Categoría: " + equipoCategoria.getCategory());
                    System.out.println(
                            "Características: "
                                    + equipoCategoria.getCharacteristic());
                }
            }
        }

        if (!hayDisponibles) {
            System.out.println("No hay equipos disponibles.");
        }
    }

    public void rentar() {
        boolean hayDisponibles = false;

        System.out.println("Equipos disponibles para rentar:");

        for (EquipModel equipo : equipos) {
            if (equipo.isDisponibility()) {
                System.out.println(
                        "Código: " + equipo.getCode()
                                + " | Modelo: " + equipo.getModel()
                                + " | Marca: " + equipo.getBrand()
                                + " | Tarifa por día: " + equipo.getFee());

                hayDisponibles = true;
            }
        }

        if (!hayDisponibles) {
            System.out.println("No hay equipos disponibles.");
            return;
        }

        System.out.println("Ingrese el código del equipo que desea rentar:");

        try {
            int codigo = Integer.parseInt(scanner.nextLine());

            for (EquipModel equipo : equipos) {
                if (equipo.getCode().equals(codigo)) {
                    if (!equipo.isDisponibility()) {
                        System.out.println("Este equipo ya está rentado.");
                        return;
                    }

                    equipo.setDisponibility(false);

                    System.out.println(
                            "Equipo rentado correctamente: " + equipo.getModel());
                    System.out.println("Estado: No disponible.");
                    return;
                }
            }

            System.out.println("No existe un equipo con ese código.");

        } catch (NumberFormatException e) {
            System.out.println("El código debe ser un número entero.");
        }
    }

    public void agregar(CategoryEquip nuevo) {
        if (nuevo == null) {
            throw new IllegalArgumentException("Debe proporcionar un equipo.");
        }

        nuevo.validar();

        for (EquipModel equipo : equipos) {
            if (equipo.getCode().equals(nuevo.getCode())) {
                throw new IllegalArgumentException(
                        "Ya existe un equipo con ese código.");
            }
        }

        equipos.add(nuevo);
    }

    public void devolver() {
        boolean hayRentados = false;

        System.out.println("Equipos rentados:");

        for (EquipModel equipo : equipos) {
            if (!equipo.isDisponibility()) {
                System.out.println(
                    "Código: " + equipo.getCode()
                    + " | Modelo: " + equipo.getModel()
                    + " | Marca: " + equipo.getBrand()
                );

                hayRentados = true;
            }
        }

        if (!hayRentados) {
            System.out.println("No hay equipos pendientes de devolución.");
            return;
        }

        System.out.println("Ingrese el código del equipo que desea devolver:");

        try {
            int codigo = Integer.parseInt(scanner.nextLine());

            for (EquipModel equipo : equipos) {
                if (equipo.getCode().equals(codigo)) {
                    if (equipo.isDisponibility()) {
                        System.out.println(
                            "Este equipo ya está disponible; no está rentado."
                        );
                        return;
                    }

                    equipo.setDisponibility(true);

                    System.out.println(
                        "Devolución registrada: " + equipo.getModel()
                    );
                    System.out.println("Estado: Disponible.");
                    return;
                }
            }

            System.out.println("No existe un equipo con ese código.");

        } catch (NumberFormatException e) {
            System.out.println("El código debe ser un número entero.");
        }
    }

    public void cargarEquiposIniciales() {
        CategoryEquip computadora = new CategoryEquip(1);
        computadora.setCode(26101);
        computadora.setModel("Latitude 5420");
        computadora.setBrand("Dell");
        computadora.setFee(150);
        computadora.setCharacteristic("Procesador Intel i5, RAM de 16 GB");

        agregar(computadora);

        CategoryEquip computadora1 = new CategoryEquip(1);
        computadora1.setCode(26103);
        computadora1.setModel("Victus N1");
        computadora1.setBrand("HP");
        computadora1.setFee(200);
        computadora1.setCharacteristic("Procesador Intel i7, RAM de 24 GB");

        agregar(computadora1);

        CategoryEquip impresora = new CategoryEquip(2);
        impresora.setCode(16102);
        impresora.setModel("EcoTank L3250");
        impresora.setBrand("Epson");
        impresora.setFee(80);
        impresora.setCharacteristic("Impresión a color, sistema de tinta");

        agregar(impresora);

        CategoryEquip impresora1 = new CategoryEquip(2);
        impresora1.setCode(16104);
        impresora1.setModel("EcoTank L3250");
        impresora1.setBrand("HP");
        impresora1.setFee(70);
        impresora1.setCharacteristic("Impresión a color");

        agregar(impresora1);

        CategoryEquip proyector = new CategoryEquip(3);
        proyector.setCode(36101);
        proyector.setModel("L520W");
        proyector.setBrand("Epson");
        proyector.setFee(125);
        proyector.setCharacteristic("Luz laser");

        agregar(proyector);

        CategoryEquip proyector1 = new CategoryEquip(3);
        proyector1.setCode(36102);
        proyector1.setModel("L630U");
        proyector1.setBrand("Epson");
        proyector1.setFee(305);
        proyector1.setCharacteristic("Pantalla interactiva");

        agregar(proyector1);
    }
}
