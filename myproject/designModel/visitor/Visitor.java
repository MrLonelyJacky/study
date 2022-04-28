package designModel.visitor;

public interface Visitor {
    /**
     * 访问工程师
     * @param engineer
     */
    void visit(Engineer engineer);

    /**
     * 访问经理
     * @param manager
     */
    void visit(Manager manager);
}
