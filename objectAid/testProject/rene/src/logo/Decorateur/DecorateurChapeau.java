package logo.Decorateur;

import logo.Affichage.MyImage;
import logo.Logo.Logo;

public class DecorateurChapeau extends LogoDecorateur {
    public DecorateurChapeau(Logo i)
    {
        super(i, 2.5, "ressource/img/Chapeau.png", 280, 42);
    }
}
