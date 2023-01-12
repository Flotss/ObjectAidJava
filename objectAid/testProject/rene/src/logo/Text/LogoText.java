package logo.Text;

import logo.Affichage.MyImage;
import logo.Logo.Logo;

public class LogoText extends Logo {
    private Logo logo;
    private String text;
    private int x;
    private int y;


    public LogoText(Logo i, int prix, String text, int x, int y) {
        super("", prix);
        this.logo = i;
        this.text = text;
        this.x = x;
        this.y = y;
    }

    @Override
    public MyImage getLogo() {
        MyImage res = this.logo.getLogo();
        res.textOver(this.text, this.x, this.y);
        return res;
    }

    @Override
    public double combienCaCoute() {
        return this.prix+this.logo.combienCaCoute();
    }
}

