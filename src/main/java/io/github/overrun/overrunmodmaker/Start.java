package io.github.overrun.overrunmodmaker;


import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubIJTheme;


public class Start {

    // 客户端  // Client
    private static OverrunModMakerClient MANGO_MOD_MAKER_CLIENT;

    public static void main(String[] args) {

        FlatGitHubIJTheme.setup();


        MANGO_MOD_MAKER_CLIENT = new OverrunModMakerClient();
        MANGO_MOD_MAKER_CLIENT.CreateWindow("Overrun Mod Maker", 540, 320);
        //-------------------------------------------------------------------------------
        MANGO_MOD_MAKER_CLIENT.setLanguage("zh_cn");
        MANGO_MOD_MAKER_CLIENT.init();
        MANGO_MOD_MAKER_CLIENT.UpdateLanguageInit();
    }
}
