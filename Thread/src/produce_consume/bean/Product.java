package produce_consume.bean;

/**
 * 产品实体
 *
 * @author BTPJ  2022/2/23
 */
public class Product {
    private String name;

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
