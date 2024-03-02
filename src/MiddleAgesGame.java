import java.util.Scanner;

// Interface for different character classes
interface CharacterClass {
    String getDescription();
}

// Concrete implementation of Wizard class
class Wizard implements CharacterClass {
    @Override
    public String getDescription() {
        return "Вы волшебник, владеющий мощной тайной магией.";
    }
}

// Concrete implementation of Knight class
class Knight implements CharacterClass {
    @Override
    public String getDescription() {
        return "Вы рыцарь, опытный в бою и поклявшийся защищать королевство.";
    }
}

// Concrete implementation of Archer class
class Archer implements CharacterClass {
    @Override
    public String getDescription() {
        return "Вы лучник, мастер дальнобойного оружия и точности.";
    }
}

// Concrete implementation of Assassin class
class Assassin implements CharacterClass {
    @Override
    public String getDescription() {
        return "Вы убийца, обученный скрытности и смертельным ударам.";
    }
}

// Interface for defining different character races
interface Race {
    String getDescription();
}

// Concrete implementation of Elf race
class Elf implements Race {
    @Override
    public String getDescription() {
        return "Вы видите стоящего перед вами эльфа, изящного и справедливого.";
    }
}

// Concrete implementation of Goblin race
class Goblin implements Race {
    @Override
    public String getDescription() {
        return "Вы столкнулись с гоблином, маленьким и хитрым.";
    }
}

// Concrete implementation of Human race
class Human implements Race {
    @Override
    public String getDescription() {
        return "Вы человек, адаптируемый и находчивый.";
    }
}

// Interface for defining characters (user and NPCs)
interface Character {
    String getDescription();

    void interact(Scanner scanner);
}

// Implementation of user character
class UserCharacter implements Character {
    private Race race;
    private CharacterClass characterClass;

    public UserCharacter(Race race, CharacterClass characterClass) {
        this.race = race;
        this.characterClass = characterClass;
    }

    @Override
    public String getDescription() {
        return race.getDescription() + " " + characterClass.getDescription();
    }

    @Override
    public void interact(Scanner scanner) {
        System.out.println("Вы слишком заняты, чтобы общаться с самим собой.");
    }
}

// Implementation of NPC character
class NPCCharacter implements Character {
    private Race race;
    private Scanner scanner;

    public NPCCharacter(Race race, Scanner scanner) {
        this.race = race;
        this.scanner = scanner;
    }

    @Override
    public String getDescription() {
        return race.getDescription();
    }

    @Override
    public void interact(Scanner scanner) {
        System.out.println("Вы встречаете NPC. Что ты хочешь делать?");
        System.out.println("1. Поговорить с NPC");
        System.out.println("2. Игнорировать NPC");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        switch (choice) {
            case 1:
                talk();
                break;
            case 2:
                ignore();
                break;
            default:
                System.out.println("Неверный выбор. Вы решаете игнорировать NPC.");
                ignore();
                break;
        }
    }

    private void talk() {
        System.out.println("Вы разговариваете с NPC.");
        System.out.println("NPC: Приветствую, путник! Что привело тебя в эти земли?");
        System.out.println("1. Спросить о местных слухах");
        System.out.println("2. Узнать о близлежащих достопримечательностях");
        System.out.println("3. Попрощаться");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        switch (choice) {
            case 1:
                System.out.println("Вы: Знаете ли вы здесь какие-нибудь интересные слухи?");
                System.out.println("NPC: Ах, я слышал шепот о драконе, скрывающемся в горах на севере.");
                break;
            case 2:
                System.out.println("Вы: Какие ориентиры мне следует знать в этой области?");
                System.out.println("NPC: Ну, за лесом, на востоке, есть старые руины замка.");
                break;
            case 3:
                System.out.println("Вы: Спасибо за информацию. Прощай!");
                System.out.println("NPC: Приятного путешествия!");
                break;
            default:
                System.out.println("Ты: Я не понимаю, о чем ты говоришь.");
                System.out.println("NPC: Всё в порядке. Возможно, в другой раз.");
                break;
        }
    }

    private void ignore() {
        System.out.println("Вы решаете игнорировать NPC и продолжить свое путешествие.");
    }
}

// Main class to start the game
public class MiddleAgesGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User selects class
        System.out.println("Выберите класс: (1) Калдун, (2) Рыцарь, (3) Лучник, (4) Убица");
        int classChoice = scanner.nextInt();
        CharacterClass characterClass;
        switch (classChoice) {
            case 1:
                characterClass = new Wizard();
                break;
            case 2:
                characterClass = new Knight();
                break;
            case 3:
                characterClass = new Archer();
                break;
            case 4:
                characterClass = new Assassin();
                break;
            default:
                System.out.println("Invalid choice, defaulting to Knight.");
                characterClass = new Knight();
        }

        // User character is a human
        Race userRace = new Human();

        Character user = new UserCharacter(userRace, characterClass);
        System.out.println("Добро пожаловть в игру про Средневековье!");
        System.out.println("Ваша раса человек. " + characterClass.getDescription());

        // Simulate interaction with NPC characters
        System.out.println("Путешествуя по миру, вы встречаете различных персонажей:");
        Character elfNPC = new NPCCharacter(new Elf(), scanner);
        elfNPC.interact(scanner);

        Character goblinNPC = new NPCCharacter(new Goblin(), scanner);
        goblinNPC.interact(scanner);

        scanner.close();
    }
}
