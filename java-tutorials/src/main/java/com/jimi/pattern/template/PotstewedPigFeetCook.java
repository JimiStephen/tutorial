package com.jimi.pattern.template;

/**
 * 开始做卤猪蹄
 */
public class PotstewedPigFeetCook extends AbsCook {
    @Override
    public void readyForMaterial() {
        System.out.println("去市场买好准备好一根猪蹄");
    }

    @Override
    public void washForMaterial() {
        System.out.println("我们把这猪蹄清清洗一下");
    }

    @Override
    public void cutForMaterial() {
        System.out.println("把猪蹄切成段。");
    }

    @Override
    public void cook() {
        System.out.println("开始进行卤！");
    }
}
