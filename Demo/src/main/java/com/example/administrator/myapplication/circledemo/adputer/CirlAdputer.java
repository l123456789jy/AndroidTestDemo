package com.example.administrator.myapplication.circledemo.adputer;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.circledemo.bean.CirlBean;

/**
 * 作业详情评论外边列表
 */
public class CirlAdputer
        extends RecyclerView.Adapter<CirlAdputer.ItemViewHolder> {

    private int lastPosition = -1;
    private CirlBean cirlBean;
    private Context context;
    LinearLayoutManager layoutManager;


    public CirlAdputer(CirlBean cirlBean, Context context) {
        this.cirlBean = cirlBean;
        this.context = context;
    }


    //记住在使用RecyclerView的时候要主页这里的返回类型！ItemViewHolder
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(),
                R.layout.item_question_comment, null);
        return new ItemViewHolder(view);//创建一个viewholder,然后将view传递进来！
    }


    @Override
    public void onBindViewHolder(final ItemViewHolder viewHolder, final int position) {
        viewHolder.tv_comment_nickname.setText(
                cirlBean.getComments().get(position).getAuthor().getNickname());
        viewHolder.tv_comment_content.setText(
                cirlBean.getComments().get(position).getContent());

        viewHolder.tv_comment_zan_nums.setText(
                cirlBean.getComments().get(position).getZan());

        viewHolder.tv_comment_dateline.setText(
                cirlBean.getComments().get(position).getDateline());

        viewHolder.tv_comment_nums.setText(
                cirlBean.getComments().get(position).getFloor() + "");
        if ( cirlBean.getComments().get(position).getChild().size()==0){
            viewHolder.ll_question_comment_replys.setVisibility(View.GONE);
        }else {
            viewHolder.ll_question_comment_replys.setVisibility(View.VISIBLE);
        }
        layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        viewHolder.lv_question_comment_reply.setHasFixedSize(true);
        viewHolder.lv_question_comment_reply.setItemAnimator(
                new DefaultItemAnimator());

        viewHolder.lv_question_comment_reply.setLayoutManager(layoutManager);
        viewHolder.lv_question_comment_reply.setAdapter(new CommentAdputer(
                cirlBean.getComments().get(position).getChild()));

        //点赞
        viewHolder.tv_comment_zan_nums.setOnClickListener(
                new View.OnClickListener() {

                    @Override public void onClick(View v) {
                        String zan = cirlBean.getComments()
                                             .get(position)
                                             .getZan();
                        int i = Integer.parseInt(zan) + 1;
                        //这里一定要把最新的点赞数重新设置进去
                        cirlBean.getComments().get(position).setZan(i + "");
                        viewHolder.tv_comment_zan_nums.setText(i + "");
                    }
                });
        //评论
        viewHolder.tv_comment_nums.setOnClickListener(
                new View.OnClickListener() {

                    @Override public void onClick(View v) {

                    }
                });
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override public int getItemCount() {
        return cirlBean.getComments().size();
    }


    public void restartAnimationPostion() {
        lastPosition = -1;
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_headImage;
        TextView tv_comment_nickname;
        TextView tv_comment_dateline;
        TextView tv_comment_index;
        TextView tv_comment_content;
        TextView tv_comment_zan_nums;
        TextView tv_comment_nums;
        RecyclerView lv_question_comment_reply;
        LinearLayout ll_question_comment_replys;

        public ItemViewHolder(View itemView) {
            super(itemView);
            iv_headImage = (ImageView) itemView.findViewById(R.id.iv_headImage);

            tv_comment_nickname = (TextView) itemView.findViewById(
                    R.id.tv_comment_nickname);
            tv_comment_dateline = (TextView) itemView.findViewById(
                    R.id.tv_comment_dateline);

            tv_comment_index = (TextView) itemView.findViewById(
                    R.id.tv_comment_index);

            tv_comment_content = (TextView) itemView.findViewById(
                    R.id.tv_comment_content);

            tv_comment_zan_nums = (TextView) itemView.findViewById(
                    R.id.tv_comment_zan_nums);

            tv_comment_nums = (TextView) itemView.findViewById(
                    R.id.tv_comment_nums);

            lv_question_comment_reply = (RecyclerView) itemView.findViewById(
                    R.id.lv_question_comment_reply);


            ll_question_comment_replys = (LinearLayout) itemView.findViewById(
                    R.id.ll_question_comment_replys);
        }
    }
}
