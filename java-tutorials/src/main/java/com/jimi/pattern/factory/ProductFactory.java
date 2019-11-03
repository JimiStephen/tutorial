package com.jimi.pattern.factory;

public   class ProductFactory extends Factory {

    /**
     * 定义一个输入产品参数就生成一个相应种类的产品；
     * @param c
     * @param <T>
     * @return
     */
    @Override
    public   <T extends Product> T createProduct(Class<T> c){

        Product product = null;

        try {
            product = (Product) Class.forName(c.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return (T)product;
    }
}
