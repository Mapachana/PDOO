package deepspace;

import java.io.PrintStream;
import java.util.ArrayList;

public class TestP2 {
    public static void main (String[] args) {
        System.out.format("\nPruebas de las clases implementadas en la segunda práctica\n");

    //
    // ─── HANGAR ─────────────────────────────────────────────────────────────────────
    //

        System.out.format("\n\nPruebas de hangar\n");

        Hangar hangar1;
        hangar1 = new Hangar(5);

        Weapon arma1, arma2, arma3, arma4;
        arma1 = new Weapon("Pistola", WeaponType.PLASMA, 2);
        arma2 = new Weapon("Cannon", WeaponType.LASER, 0);
        arma3 = new Weapon("Misil", WeaponType.PLASMA, 5);

        ShieldBooster potenciador1, potenciador2, potenciador3;
        potenciador1 = new ShieldBooster("Pinchos",4,1);
        potenciador2 = new ShieldBooster("Recubrimiento",5,5);
        potenciador3 = new ShieldBooster("Hierro",2,4);

        System.out.format("\n%s\n",arma1.toString());

        hangar1.addWeapon(arma1);
        hangar1.addShieldBooster(potenciador1);
        hangar1.addWeapon(arma2);
        hangar1.addShieldBooster(potenciador2);
        hangar1.addWeapon(arma3);
        hangar1.addShieldBooster(potenciador3);

        System.out.format("\n%s\n",hangar1.toString());

        Hangar hangar2;
        hangar2 = new Hangar(hangar1);

        hangar2.removeShieldBooster(1);
        hangar2.removeWeapon(2);

        System.out.format("\n%s\n",hangar2.toString());

    //
    // ─── DAMAGE ─────────────────────────────────────────────────────────────────────
    //

        System.out.format("\n\nPruebas de damage\n");

        Damage damage1, damage2, damage3;
        ArrayList <WeaponType> wl=new ArrayList<>();
        damage1 = new NumericDamage(5,4);
        damage2 = new SpecificDamage(wl, 2);
        wl.add(WeaponType.LASER);
        wl.add(WeaponType.MISSILE);
        wl.add(WeaponType.PLASMA);

        System.out.format("\n%s\n",damage1.toString());
        System.out.format("\n%s\n",damage2.toString());

        //damage3 = damage1.adjust(hangar1.getWeapons(), hangar1.getShieldBoosters());
        //System.out.format("\n%s\n",damage3.toString());
        //damage3 = damage2.adjust(hangar1.getWeapons(), hangar1.getShieldBoosters());
        //System.out.format("\n%s\n",damage3.toString());

        //damage1.discardWeapon(arma1);
        //damage2.discardWeapon(arma3);
        damage1.discardShieldBooster();
        damage2.discardShieldBooster();

        System.out.format("\nTras descartes\n");
        System.out.format("\n%s\n",damage1.toString());
        System.out.format("\n%s\n",damage2.toString());



    //
    // ─── HASNOEFFECT ────────────────────────────────────────────────────────────────
    //

        ArrayList<WeaponType> vacio = new ArrayList<>();
        Damage damagenull;

        System.out.format("\n\nPrueba de hasNoEffect:");
        damagenull = new SpecificDamage(vacio,0);

        if(damagenull.hasNoEffect())
            System.out.format("\nFunciona\n");

        damagenull = new NumericDamage(0,0);

        if(damagenull.hasNoEffect())
            System.out.format("\nTambien Funciona\n");

        if(!damage1.hasNoEffect())
            System.out.format("\nSigue funcionando\n");

        if(!damage2.hasNoEffect())
            System.out.format("\nDefinitivamente funciona.\n");


    //
    // ─── ENEMYSTARSHIP ──────────────────────────────────────────────────────────────
    //

        System.out.format("\n\nPrueba de EnemyStarShip\n");


        Loot loot;
        EnemyStarShip enemystarship;

        loot          = new Loot(3, 2, 4, 2, 7);
        enemystarship = new EnemyStarShip("HalconMilenario",30,25,loot,damage2);

        System.out.format("\n%s\n",enemystarship.toString());

        if(enemystarship.receiveShot(30)==ShotResult.RESIST)
            System.out.format("\nNo Funciona\n");
        else
            if(enemystarship.receiveShot(20)==ShotResult.RESIST && enemystarship.receiveShot(30)==ShotResult.DONOTRESIST)
            System.out.format("\nFunciona\n");

    //
    // ─── SPACESTATION ───────────────────────────────────────────────────────────────
    //


        System.out.format("\n\n\nPrueba de SpaceStation\n");

        SuppliesPackage suppliesPackage, suppliesPackage1;
        suppliesPackage  = new SuppliesPackage(30,150,25);
        suppliesPackage1 = new SuppliesPackage(2,20,17);

        SpaceStation spaceStation;
        spaceStation = new SpaceStation("Skylab",suppliesPackage);

        spaceStation.setPendingDamage(damage1);
        spaceStation.receiveHangar(hangar1);
        System.out.format("\n%s\n",spaceStation.toString());
        spaceStation.move();
        spaceStation.receiveSupplies(suppliesPackage1);
        spaceStation.move();
        System.out.format("\n%s\n",spaceStation.toString());

        if(spaceStation.validState())
            System.out.format("\nLa estacion está bien\n");

        arma4        = new Weapon("Bazooka",WeaponType.MISSILE,5);
        potenciador3 = new ShieldBooster("CapaExtra",5,7);

        spaceStation.mountWeapon(0);
        spaceStation.mountWeapon(0);
        spaceStation.mountWeapon(0);
        spaceStation.mountShieldBooster(0);
        spaceStation.mountShieldBooster(0);
        System.out.format("\n\nDespues de montarlo todo: %s\n", spaceStation.toString());


        if (spaceStation.receiveShieldBooster(potenciador3) && spaceStation.receiveWeapon(arma4))
            System.out.format("\n\nDespues de añadir un potenciador y un arma: %s\n", spaceStation.toString());

        spaceStation.discardWeaponInHangar(0);
        spaceStation.discardShieldBoosterInHangar(0);
        System.out.format("\n\nDespues de hacer unos descartes en el hangar: %s\n", spaceStation.toString());

        spaceStation.getWeapons().get(0).useIt();
        spaceStation.getWeapons().get(0).useIt();

        spaceStation.cleanUpMountedItems();
        System.out.format("\n\nDespués de hacer unos usos y una limpieza: %s\n", spaceStation.toString());

    }
}
