package com.shantoo.develop.library.ui.widget.treeview;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者: shantoo on 2017/5/23 11:39.
 */

public class TreeHelper {

    private static class ViewHolder{
        static int mExIcon;
        static int mEcIcon;
    }

    public static void setNodeIcon(int exIcon,int ecIcon){
        ViewHolder.mExIcon = exIcon;
        ViewHolder.mEcIcon = ecIcon;
    }

    /**
     * 过滤出所有可见的Node
     *
     * @param nodes
     * @return
     */
    public static List<Node> filterVisibleNode(List<Node> nodes) {
        List<Node> result = new ArrayList<>();
        for (Node node : nodes) {
            // 如果为跟节点，或者上层目录为展开状态
            if (node.isRoot() || node.isParentExpand()) {
                setNodeIcon(node);
                result.add(node);
            }
        }
        return result;
    }

    /**
     * 把一个节点上的所有的内容都挂上去
     */
    private static void addNode(List<Node> nodes, Node node,
                               int defaultExpandLeval, int currentLevel) {
        nodes.add(node);
        if (defaultExpandLeval >= currentLevel) {
            node.setExpand(true);
        }
        if (node.isLeaf())
            return;
        for (int i = 0; i < node.getChildren().size(); i++) {
            addNode(nodes, node.getChildren().get(i), defaultExpandLeval, currentLevel + 1);
        }
    }

    private static List<Node> getRootNodes(List<Node> nodes) {
        List<Node> root = new ArrayList<>();
        for (Node node : nodes) {
            if (node.isRoot())
                root.add(node);
        }
        return root;
    }

    /**
     * 将我们的数据转化为树的节点
     *
     * @param datas
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    private static <T> List<Node> convetData2Node(List<T> datas)
            throws IllegalArgumentException, IllegalAccessException {
        List<Node> nodes = new ArrayList<>();
        Node node;
        for (T t : datas) {
            int id = -1;
            int pId = -1;
            String label = null;
            Class<? extends Object> clazz = t.getClass();
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field f : declaredFields) {
                if (f.getAnnotation(TreeNodeId.class) != null) {
                    f.setAccessible(true);
                    id = f.getInt(t);
                }
                if (f.getAnnotation(TreeNodePid.class) != null) {
                    f.setAccessible(true);
                    pId = f.getInt(t);
                }
                if (f.getAnnotation(TreeNodeLabel.class) != null) {
                    f.setAccessible(true);
                    label = (String) f.get(t);
                }
                if (id != -1 && pId != -1 && label != null) {
                    break;
                }
            }
            node = new Node(id, pId, label);
            nodes.add(node);
        }

        /**
         * 设置Node间，父子关系;让每两个节点都比较一次，即可设置其中的关系
         */
        for (int i = 0; i < nodes.size(); i++) {
            Node n = nodes.get(i);
            for (int j = i + 1; j < nodes.size(); j++) {
                Node m = nodes.get(j);
                if (m.getpId() == n.getId()) {
                    n.getChildren().add(m);
                    m.setParent(n);
                } else if (m.getId() == n.getpId()) {
                    m.getChildren().add(n);
                    n.setParent(m);
                }
            }
        }
        // 设置图片
        for (Node n : nodes) {
            setNodeIcon(n);
        }
        return nodes;
    }

    /**
     * 传入我们的普通bean，转化为我们排序后的Node
     *
     * @param datas
     * @return
     * @throws IllegalArgumentException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static <T> List<Node> getSortedNodes(List<T> datas, int defaultExpandLevel)
                                                                throws IllegalArgumentException,
            IllegalAccessException {
        List<Node> result = new ArrayList<>();
        List<Node> nodes = convetData2Node(datas);
        List<Node> rootNodes = getRootNodes(nodes);
        for (Node node : rootNodes) {
            addNode(result, node, defaultExpandLevel, 1);
        }
        return result;
    }

    /**
     * 设置节点的图标
     *
     * @param node
     */
    private static void setNodeIcon(Node node) {
        if (node.getChildren().size() > 0 && node.isExpand()) {
            node.setIcon(ViewHolder.mExIcon);
        } else if (node.getChildren().size() > 0 && !node.isExpand()) {
            node.setIcon(ViewHolder.mEcIcon);
        } else{
            node.setIcon(-1);
        }
    }
}
