package com.example.administrator.myapplication.circledemo.adputer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.circledemo.bean.CirlBean;
import java.util.List;

/**
 * 作业详情评论里面的评论
 */
public class CommentAdputer
        extends RecyclerView.Adapter<CommentAdputer.ItemViewHolder> {

    private int lastPosition = -1;
    private List<CirlBean.CommentsEntity.ChildEntity> child;


    public CommentAdputer(List<CirlBean.CommentsEntity.ChildEntity> child) {
        this.child = child;
    }


    //记住在使用RecyclerView的时候要主页这里的返回类型！ItemViewHolder
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(),
                R.layout.item_question_comment_reply, null);

        return new ItemViewHolder(view);//创建一个viewholder,然后将view传递进来！
    }


    @Override
    public void onBindViewHolder(final ItemViewHolder viewHolder, final int position) {
        viewHolder.tv_comment_reply_nickname1.setText(
                child.get(position).getRName());
        viewHolder.tv_comment_reply_content.setText(
                child.get(position).getContent());
        viewHolder.tv_comment_reply_dateline.setText(
                child.get(position).getDateline());
        viewHolder.tv_comment_reply_index.setText(position + "楼");
        viewHolder.btn_comment_reply.setOnClickListener(
                new View.OnClickListener() {

                    //这正确的逻辑是评论成功之后从服务器把这段评论返回然后刷新当前的评论列表
                    @Override public void onClick(View v) {
                        CirlBean.CommentsEntity.ChildEntity childEntity
                                = child.get(position);
                        childEntity.setContent("ceec eeecececeeccece");
                        child.add(child.size() - 1, childEntity);

                        notifyDataSetChanged();
                    }
                });
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override public int getItemCount() {
        return child.size();
    }


    public void restartAnimationPostion() {
        lastPosition = -1;
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_comment_reply_nickname1;
        TextView tv_comment_reply_nickname_hf;
        TextView tv_comment_reply_nickname2;
        TextView tv_comment_reply_index;
        TextView tv_comment_reply_content;
        TextView tv_comment_reply_dateline;
        Button btn_comment_reply;


        public ItemViewHolder(View itemView) {
            super(itemView);

            tv_comment_reply_nickname1 = (TextView) itemView.findViewById(
                    R.id.tv_comment_reply_nickname1);

            tv_comment_reply_nickname_hf = (TextView) itemView.findViewById(
                    R.id.tv_comment_reply_nickname_hf);

            tv_comment_reply_nickname2 = (TextView) itemView.findViewById(
                    R.id.tv_comment_reply_nickname2);

            tv_comment_reply_index = (TextView) itemView.findViewById(
                    R.id.tv_comment_reply_index);

            tv_comment_reply_content = (TextView) itemView.findViewById(
                    R.id.tv_comment_reply_content);

            tv_comment_reply_dateline = (TextView) itemView.findViewById(
                    R.id.tv_comment_reply_dateline);

            btn_comment_reply = (Button) itemView.findViewById(
                    R.id.btn_comment_reply);
        }
    }
}
