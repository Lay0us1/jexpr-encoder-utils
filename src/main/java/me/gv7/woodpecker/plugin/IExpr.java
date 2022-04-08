package me.gv7.woodpecker.plugin;

public interface IExpr {
    String getName();

    String[] genDnslog(String domain);

    String[] genHttplog(String url);

    String[] genSleep(int sec);

    String[] genExec(String command);

    String[] genExecWithEcho(String command);
}
