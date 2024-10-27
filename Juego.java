import java.util.Scanner;

public class Juego {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Crear héroes (superhéroes) y villanos del universo Naruto Shippuden
        SuperHero naruto = new SuperHero("Naruto Uzumaki", 50, 60, 200);
        SuperHero sasuke = new SuperHero("Sasuke Uchiha", 55, 65, 190);
        SuperHero sakura = new SuperHero("Sakura Haruno", 40, 50, 180);

        Villano madara = new Villano("Madara Uchiha", 70, 75, 250);
        Villano orochimaru = new Villano("Orochimaru", 45, 55, 210);
        Villano pain = new Villano("Pain", 60, 70, 220);

        // Lista de héroes y villanos
        SuperHero[] heroes = { naruto, sasuke, sakura };
        Villano[] villanos = { madara, orochimaru, pain };

        // Elegir un héroe
        System.out.println("Elige tu héroe:");
        for (int i = 0; i < heroes.length; i++) {
            System.out.println((i + 1) + ". " + heroes[i].nombre);
        }
        int eleccionHeroe = scanner.nextInt();
        SuperHero heroeElegido = heroes[eleccionHeroe - 1];

        // Elegir un villano
        System.out.println("Elige el villano a enfrentar:");
        for (int i = 0; i < villanos.length; i++) {
            System.out.println((i + 1) + ". " + villanos[i].nombre);
        }
        int eleccionVillano = scanner.nextInt();
        Villano villanoElegido = villanos[eleccionVillano - 1];

        // Mostrar estadísticas iniciales
        heroeElegido.mostrarEstadisticas();
        villanoElegido.mostrarEstadisticas();

        // Ciclo para que el usuario controle las acciones
        while (heroeElegido.vida_hp > 0 && villanoElegido.vida_hp > 0) {
            System.out.println("\nElige la acción de tu héroe:");
            System.out.println("1. Atacar");
            System.out.println("2. Defender");
            System.out.println("3. Recuperarse");
            System.out.println("4. Mostrar estadísticas");

            int eleccion = scanner.nextInt();

            switch (eleccion) {
                case 1:
                    System.out.println("¿Quieres usar poder extra? (si/no): ");
                    String usarPoderExtra = scanner.next();
                    if (usarPoderExtra.equalsIgnoreCase("si")) {
                        System.out.println("Introduce la cantidad de poder extra: ");
                        int poderExtra = scanner.nextInt();
                        heroeElegido.atacar(villanoElegido, poderExtra);
                    } else {
                        heroeElegido.atacar(villanoElegido);
                    }
                    break;
                case 2:
                    System.out.println("Defenderse contra el ataque del villano.");
                    heroeElegido.defender(villanoElegido.fuerza);
                    break;
                case 3:
                    heroeElegido.recuperarse();
                    break;
                case 4:
                    heroeElegido.mostrarEstadisticas();
                    villanoElegido.mostrarEstadisticas();
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

            // Si el villano sigue vivo, ataca
            if (villanoElegido.vida_hp > 0) {
                System.out.println("\nEs el turno del villano.");
                villanoElegido.atacar(heroeElegido);
                if (heroeElegido.vida_hp <= 0) {
                    System.out.println(heroeElegido.nombre + " ha sido derrotado.");
                    break;
                }
            } else {
                System.out.println(villanoElegido.nombre + " ha sido derrotado.");
            }

            // Mostrar estadísticas después del turno
            heroeElegido.mostrarEstadisticas();
            villanoElegido.mostrarEstadisticas();
        }

        // Fin del juego
        System.out.println("¡Fin del juego!");
        scanner.close();
    }
}
