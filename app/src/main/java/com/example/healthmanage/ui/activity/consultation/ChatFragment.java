package com.example.healthmanage.ui.activity.consultation;

import com.example.healthmanage.R;
import com.hyphenate.easeui.modules.chat.EaseChatFragment;
import com.hyphenate.easeui.modules.chat.interfaces.IChatExtendMenu;

public class ChatFragment extends EaseChatFragment {
    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void initData() {
        super.initData();
        resetChatExtendMenu();
    }
    private void resetChatExtendMenu() {
        IChatExtendMenu chatExtendMenu = chatLayout.getChatInputMenu().getChatExtendMenu();
        chatExtendMenu.clear();
        chatExtendMenu.registerMenuItem(R.string.attach_picture, R.drawable.ease_chat_image_selector, R.id.extend_item_picture);
        chatExtendMenu.registerMenuItem(R.string.attach_take_pic, R.drawable.ease_chat_takepic_selector, R.id.extend_item_take_picture);
    }

}
