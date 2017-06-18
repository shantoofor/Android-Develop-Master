package com.shantoo.develop.library.ui.widget.treeview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.List;

public abstract class TreeListViewAdapter<T> extends BaseAdapter implements OnItemClickListener{

    protected Context mContext;
    protected List<Node> mNodes;
    protected List<Node> mAllNodes;
    protected LayoutInflater mInflater;

    private OnTreeNodeClickListener onTreeNodeClickListener;

    public interface OnTreeNodeClickListener {
        void onClick(Node node, int position);
    }

    public void setOnTreeNodeClickListener(
            OnTreeNodeClickListener onTreeNodeClickListener) {
        this.onTreeNodeClickListener = onTreeNodeClickListener;
    }

    /**
     * @param context 上下文环境
     * @param exIcon TreeView 收起时显示的图标
     * @param ecIcon TreeView 展开时显示的图标
     * @param mTree
     * @param datas
     * @param defaultExpandLevel 默认展开几级树
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public TreeListViewAdapter(Context context,int exIcon,int ecIcon,ListView mTree,
                               List<T> datas, int defaultExpandLevel)
                                        throws IllegalArgumentException, IllegalAccessException {
        mContext = context;
        TreeHelper.setNodeIcon(exIcon,ecIcon);
        mAllNodes = TreeHelper.getSortedNodes(datas, defaultExpandLevel);
        mNodes = TreeHelper.filterVisibleNode(mAllNodes);
        mInflater = LayoutInflater.from(context);
        mTree.setOnItemClickListener(this);
    }

    /***/
    public TreeListViewAdapter(Context context,ListView mTree, List<T> datas,
                               int defaultExpandLevel) throws IllegalArgumentException,
            IllegalAccessException {
        this(context,0,0,mTree,datas,defaultExpandLevel);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        expandOrCollapse(position);
        if (onTreeNodeClickListener != null) {
            onTreeNodeClickListener.onClick(mNodes.get(position), position);
        }
    }

    /**
     * 相应ListView的点击事件
     *
     * @param position
     */
    public void expandOrCollapse(int position) {
        Node n = mNodes.get(position);
        if (n != null)// 排除传入参数错误异常
        {
            if (!n.isLeaf()) {
                n.setExpand(!n.isExpand());
                mNodes = TreeHelper.filterVisibleNode(mAllNodes);
                notifyDataSetChanged();// 刷新视图
            }
        }
    }

    @Override
    public int getCount() {
        return mNodes.size();
    }

    @Override
    public Object getItem(int position) {
        return mNodes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Node node = mNodes.get(position);
        convertView = getConvertView(node, position, convertView, parent);
        convertView.setPadding(node.getLevel() * 30, 3, 3, 3);
        return convertView;
    }

    public abstract View getConvertView(Node node, int position, View convertView, ViewGroup parent);
}
