package logo;


import logo.Decorateur.DecorateurCandy;
import logo.Decorateur.DecorateurChapeau;
import logo.Decorateur.DecorateurLunette;
import logo.Decorateur.DecorateurSmiley;
import logo.Logo.CrazyFrog;
import logo.Logo.Logo;
import logo.Logo.ReneLaTaupe;
import logo.Text.TextRenelaTaupe;

public class Main
{
   
    public static void main(String args[])
    {
//        ReneLaTaupe l = new ReneLaTaupe();
//        MyImage i = l.getLogo();
//
//        i.paintOver("ressource/img/Chapeau.png", 280,42);
//        i.paintOver("ressource/img/Sunglasses.png", 255,76);
//
//        i.display();  // Permet l'affichage dans une fenetre de l'image associee
//

//        Logo i = new ReneLaTaupe();
//        i = new DecorateurChapeau(i);
//        i = new DecorateurLunette(i);
//        i = new DecorateurCandy(i);
//        i = new DecorateurSmiley(i);
//
//        i.getLogo().display();  // Permet l'affichage dans une fenetre de l'image associee
//
//        System.out.println("Prix : " + i.combienCaCoute());
//
//        Logo f = new CrazyFrog();
//        f = new DecorateurChapeau(f);
//        f = new DecorateurLunette(f);
//        f = new DecorateurCandy(f);
//        f = new DecorateurSmiley(f);
//        f.getLogo().display();
//        System.out.println("Prix : " + f.combienCaCoute());


        Logo fLine = new DecorateurSmiley(new DecorateurLunette(new DecorateurChapeau(new CrazyFrog())));
        fLine.getLogo().display();
        System.out.println("Prix : " + fLine.combienCaCoute());

        fLine = new TextRenelaTaupe(fLine);
        fLine = new TextRenelaTaupe(fLine);
        fLine.getLogo().display();

    }
        
}
