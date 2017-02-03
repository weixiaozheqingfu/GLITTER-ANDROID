package com.glitter.login;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.glitter.banch.Banch;
import com.glitter.banch.BanchImpl;
import com.glitter.base.BaseActivity;
import com.glitter.login.service.LoginService;

@SuppressLint("ShowToast")
public class LoginActivity extends BaseActivity{
	// TODO ���浽�ļ����û���������Ҫ����,��ȡ���ٽ��н���
	// TODO Ӧ����һ������ҳ��,���û����ʱ,��ʹ���Զ���¼,Ҳ�ܷ������ص���¼ҳ�档
	// TODO ��ʵֻҪ�ж����Ǹ���ť�������¼�,��ô��¼ҳ���Զ���¼�����ˡ�
	// TODO �˳�Ӧ�õĴ��뻹û��д��
	private static final String tag = "MainActivity";
	private EditText etUserName;
	private EditText etUserPassword;
	private CheckBox cbRememberPassword;
	private CheckBox cbAutomatic; 
 	private Button btnLand;
 	private ProgressBar pgBar;
 	private SharedPreferences sp;
 	private Banch banch;

 	@SuppressWarnings("static-access")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		etUserName = (EditText)this.findViewById(R.id.login_et_user_name);
		etUserPassword = (EditText)this.findViewById(R.id.login_et_user_password);
		cbRememberPassword = (CheckBox)this.findViewById(R.id.login_cb_remember_password);
		cbAutomatic = (CheckBox)this.findViewById(R.id.login_cb_automatic);
		btnLand = (Button)this.findViewById(R.id.login_btn_land);
		pgBar = (ProgressBar)this.findViewById(R.id.login_pgBar);
		// ��ȡ��¼����ƫ����Ϣ
	    sp = this.getSharedPreferences("login", this.MODE_PRIVATE);
	    // �õ���ת��������
	    banch = new BanchImpl();
	    
    	String userName = sp.getString("userName", "");
    	// TODO �����ȡ��������ʵӦ���Ǿ�������base64�ȼ����㷨���ܺ����������
		String password = sp.getString("password", "");
		boolean isRemember = sp.getBoolean("isRemember",false);
		boolean isAutomaticLogin = sp.getBoolean("automaticLogin",false);
		etUserName.setText(userName);
		etUserPassword.setText(password);	
		cbRememberPassword.setChecked(isRemember);
		cbAutomatic.setChecked(isAutomaticLogin);
		
		// ������Զ���¼��
		if(isAutomaticLogin){
			// ���Զ���ת����ҳ�ҳ��
			banch.jumpActivity(this,HomeActivity.class);
		}
		
		// Ϊ�û��������ע�����¼�
		etUserName.setOnClickListener(this);
		// Ϊ���������ע�����¼�
		etUserPassword.setOnClickListener(this);
		// Ϊ��½��ťע��õ��ʱ�ļ���ִ�ж���,�Ա㵱����¼�����ʱ,�������������еļ�������
		btnLand.setOnClickListener(this);
		// Ϊ"��ס����"��ѡ��ע��ѡ��״̬��������¼�
		cbRememberPassword.setOnCheckedChangeListener(this);
		// Ϊ"�Զ���¼"��ѡ��ע��ѡ��״̬��������¼�
		cbAutomatic.setOnCheckedChangeListener(this);
	}

	@Override
	public void onClick(View v) {
		// ������û�������¼�
		if(R.id.login_et_user_name==v.getId()){
			etUserName.setHint(null);
		}
		// ���������������¼�
		if(R.id.login_et_user_password==v.getId()){
			etUserPassword.setHint(null);
		}
		
		// ����ǵ�½��ť������onClick�¼�
		if(R.id.login_btn_land == v.getId()){
			// TODO ��֪��Ϊʲô���ǲ���Ч,�����ٺúò�һ�½�������ʵ��,��ȡ���ɸ�qqһ����Ч��
			pgBar.setVisibility(View.VISIBLE);

			String userName = etUserName.getText().toString().trim();
			String userPassword = etUserPassword.getText().toString().trim();
			if(TextUtils.isEmpty(userName)){
				pgBar.setVisibility(View.INVISIBLE);
				Toast.makeText(this,"�������û�����",Toast.LENGTH_SHORT).show();
				return;
			}
			if(TextUtils.isEmpty(userPassword)){
				pgBar.setVisibility(View.INVISIBLE);
				Toast.makeText(this,"���������룡",Toast.LENGTH_SHORT).show();
				return;
			}
			
			// TODO ��ʵ��������Ӧ�ý��û��������ύ���������ˡ��������˽��յ�����Զ���û�¼�����ԭʼ����������,Ȼ���ڷ�������MD5�������ݿ��ж�Ӧ��������бȶԡ�
			
			// һ���Ƿ������˴����½����,��������Ƿ������˵�½�ɹ���ͻ���Ҫ�������������ת����ҳ�ͼ�ס����
			if("admin".equals(userName) && ("888888".equals(userPassword))){
				Toast.makeText(this, "��½�ɹ���", Toast.LENGTH_SHORT).show();
				// ��ת����ҳ
				banch.jumpActivity(this,HomeActivity.class);
				// ��¼�ɹ������û���¼ʱѡ���ƫ��������Ϣ
				// �����ѡ��ס�Ҹ�ѡ��
				if(cbRememberPassword.isChecked()){
					Log.i(tag,"��Ҫ�����¼ƫ��");
					// ��������������ɹ���,�᷵�ص�½�ɹ���ʶ���ͻ���,�ͻ��˽����ͻ����û�¼�����ԭʼ����������ͨ�������㷨����base64�㷨���ܺ�д���¼ƫ���ļ�
					boolean result = LoginService.saveLoginInfo(this, userName,userPassword,cbRememberPassword.isChecked(),cbAutomatic.isChecked());
					if(result){
						Log.i(tag,"�����¼ƫ�óɹ���");
					}else{
						Log.i(tag,"�����¼ƫ��ʧ�ܣ�");
					}
				}else{
					Log.i(tag,"��½ʱ����Ҫ�����¼ƫ�ã������¼ƫ����Ϣ...");
					boolean result = LoginService.saveLoginInfo(this,"","",cbRememberPassword.isChecked(),cbAutomatic.isChecked());
					if(result){
						Log.i(tag,"��½ʱ����Ҫ�����¼ƫ�ã������¼ƫ����Ϣ�ɹ�...");
					}else{
						Log.i(tag,"��½ʱ����Ҫ�����¼ƫ�ã������¼ƫ����Ϣʧ��...");
					}
				}
			}else{
				Toast.makeText(this, "�û������������", Toast.LENGTH_SHORT).show();
			}
			pgBar.setVisibility(View.INVISIBLE);
		}
	}
	
	/**
	 * checkBox��ѡ��״̬�ı��¼�
	 * 
	 * @param buttonView ��checkBox����
	 * @param isCheck �������checkBox���Ƿ�ѡ��״̬
	 * @return void
	 */
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// �����"�Զ���¼"��ѡ�����¼�
		if(R.id.login_cb_automatic==buttonView.getId()){
			// ���״̬���Ϊѡ��
			if(isChecked){
				cbRememberPassword.setChecked(true);
			}
		}
		// �����"��ס����"��ѡ�����¼�
		if(R.id.login_cb_remember_password==buttonView.getId()){
			// ���״̬���Ϊѡ��
			if(!isChecked){
				cbAutomatic.setChecked(false);
			}
		}
	}
}
