package com.ginko.activity.entity;

import android.content.Context;
import android.content.res.Resources;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.ginko.activity.im.MessageItemView;
import com.ginko.common.MyDataUtils;
import com.ginko.ginko.MyApp;
import com.ginko.ginko.R;
import com.ginko.vo.EntityMessageVO;
import com.ginko.vo.MultimediaMessageVO;


public class EntityPostLocationMessageItemView extends MessageItemView{
    private LinearLayout rootLayout;
    private RelativeLayout messageContentLayout;
    private TextView txtSendTime ,txtEntityName;
    private NetworkImageView imgEntityPhoto;

    private ImageLoader imgLoader;

    private ImageView btnViewOnlyOne;

    private EntityMessageVO msgItem;

    public EntityPostLocationMessageItemView(Context context) {
        super(context);
    }

    public EntityPostLocationMessageItemView(Context context, EntityMessageVO messageItem) {
        super(context, messageItem);

        this.msgItem = messageItem;

        inflater.inflate(R.layout.entity_view_message_item_location, this, true);

        txtSendTime = (TextView)findViewById(R.id.txtSendTime);

        rootLayout = (LinearLayout)findViewById(R.id.rootLayout);
        messageContentLayout = (RelativeLayout)findViewById(R.id.messageContentLayout);

        txtSendTime = (TextView)findViewById(R.id.txtTime);
        txtEntityName = (TextView)findViewById(R.id.txtEntityName);

        imgEntityPhoto = (NetworkImageView)findViewById(R.id.imgEntityPhoto);
        imgEntityPhoto.setDefaultImageResId(R.drawable.entity_dummy);

        btnViewOnlyOne = (ImageView)findViewById(R.id.viewOnlyOne);
    }

    @Override
    public void getUIObjects(boolean isSelectable)
    {
        Resources res = mContext.getResources();

        this.msgItem = (EntityMessageVO)getMessageItem();

        messageContentLayout.setPadding(res.getDimensionPixelSize(R.dimen.entity_view_message_content_layout_padding),
                res.getDimensionPixelSize(R.dimen.entity_view_message_content_layout_padding),
                res.getDimensionPixelSize(R.dimen.entity_view_message_content_layout_padding),
                res.getDimensionPixelSize(R.dimen.entity_view_message_content_layout_padding));

        RelativeLayout.LayoutParams messageLayoutParam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT , RelativeLayout.LayoutParams.WRAP_CONTENT);
        messageLayoutParam.setMargins(res.getDimensionPixelSize(R.dimen.entity_view_message_content_left_right_margin) ,
                0 ,
                res.getDimensionPixelSize(R.dimen.entity_view_message_content_left_right_margin) ,
                0);
        messageLayoutParam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT );
        messageLayoutParam.addRule(RelativeLayout.BELOW , R.id.imgEntityPhoto);
        messageContentLayout.setLayoutParams(messageLayoutParam);

        rootLayout.requestLayout();
    }

    @Override
    public void setMessageItem(MultimediaMessageVO messageItem)
    {
        super.setMessageItem(messageItem);
        this.msgItem = (EntityMessageVO) getMessageItem();
    }
    @Override
    public void refreshView(boolean isSelectable)
    {
        super.refreshView(isSelectable);
        if (imgLoader == null) {
            imgLoader = MyApp.getInstance().getImageLoader();
        }

        messageContentLayout.setBackgroundResource(R.drawable.entity_post_message_item_bg);

        imgEntityPhoto.setImageUrl(msgItem.strProfilePhoto, imgLoader);
        txtEntityName.setText(msgItem.strEntityName);

        txtSendTime.setText(MyDataUtils.amPmFormat(msgItem.getSendTime()));

    }
}
