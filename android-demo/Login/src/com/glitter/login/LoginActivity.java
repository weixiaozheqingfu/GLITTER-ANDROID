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
	// TODO 保存到文件的用户名和密码要加密,获取后再进行解密
	// TODO 应该在一个功能页面,等用户点击时,即使是自动登录,也能返回跳回到登录页面。
	// TODO 其实只要判断是那个按钮触发的事件,那么登录页不自动登录就是了。
	// TODO 退出应用的代码还没有写。
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
		// 读取登录界面偏好信息
	    sp = this.getSharedPreferences("login", this.MODE_PRIVATE);
	    // 得到跳转帮助对象
	    banch = new BanchImpl();
	    
    	String userName = sp.getString("userName", "");
    	// TODO 这里获取的密码其实应该是经过比如base64等加密算法解密后的明文密码
		String password = sp.getString("password", "");
		boolean isRemember = sp.getBoolean("isRemember",false);
		boolean isAutomaticLogin = sp.getBoolean("automaticLogin",false);
		etUserName.setText(userName);
		etUserPassword.setText(password);	
		cbRememberPassword.setChecked(isRemember);
		cbAutomatic.setChecked(isAutomaticLogin);
		
		// 如果是自动登录，
		if(isAutomaticLogin){
			// 则自动跳转到首页活动页面
			banch.jumpActivity(this,HomeActivity.class);
		}
		
		// 为用户名输入框注册点击事件
		etUserName.setOnClickListener(this);
		// 为密码输入框注册点击事件
		etUserPassword.setOnClickListener(this);
		// 为登陆按钮注册好点击时的监听执行对象,以便当点击事件发生时,出发监听对象中的监听方法
		btnLand.setOnClickListener(this);
		// 为"记住密码"复选框注册选中状态变更触发事件
		cbRememberPassword.setOnCheckedChangeListener(this);
		// 为"自动登录"复选框注册选中状态变更触发事件
		cbAutomatic.setOnCheckedChangeListener(this);
	}

	@Override
	public void onClick(View v) {
		// 如果是用户名点击事件
		if(R.id.login_et_user_name==v.getId()){
			etUserName.setHint(null);
		}
		// 如果是密码名点击事件
		if(R.id.login_et_user_password==v.getId()){
			etUserPassword.setHint(null);
		}
		
		// 如果是登陆按钮触发的onClick事件
		if(R.id.login_btn_land == v.getId()){
			// TODO 不知道为什么就是不生效,将来再好好查一下进度条的实现,争取做成跟qq一样的效果
			pgBar.setVisibility(View.VISIBLE);

			String userName = etUserName.getText().toString().trim();
			String userPassword = etUserPassword.getText().toString().trim();
			if(TextUtils.isEmpty(userName)){
				pgBar.setVisibility(View.INVISIBLE);
				Toast.makeText(this,"请输入用户名！",Toast.LENGTH_SHORT).show();
				return;
			}
			if(TextUtils.isEmpty(userPassword)){
				pgBar.setVisibility(View.INVISIBLE);
				Toast.makeText(this,"请输入密码！",Toast.LENGTH_SHORT).show();
				return;
			}
			
			// TODO 真实情况这里就应该将用户名密码提交到服务器了。服务器端接收到的永远是用户录入的最原始的明文密码,然后在服务器端MD5后与数据库中对应的密码进行比对。
			
			// 一般是服务器端处理登陆流程,下面假设是服务器端登陆成功后客户端要做的事情比如跳转到首页和记住密码
			if("admin".equals(userName) && ("888888".equals(userPassword))){
				Toast.makeText(this, "登陆成功！", Toast.LENGTH_SHORT).show();
				// 跳转到首页
				banch.jumpActivity(this,HomeActivity.class);
				// 登录成功保存用户登录时选择的偏好设置信息
				// 如果勾选记住我复选框
				if(cbRememberPassword.isChecked()){
					Log.i(tag,"需要保存登录偏好");
					// 服务器真正保存成功后,会返回登陆成功标识到客户端,客户端将将客户端用户录入的最原始的明文密码通过可逆算法比如base64算法加密后写入登录偏好文件
					boolean result = LoginService.saveLoginInfo(this, userName,userPassword,cbRememberPassword.isChecked(),cbAutomatic.isChecked());
					if(result){
						Log.i(tag,"保存登录偏好成功！");
					}else{
						Log.i(tag,"保存登录偏好失败！");
					}
				}else{
					Log.i(tag,"登陆时不需要保存登录偏好，清除登录偏好信息...");
					boolean result = LoginService.saveLoginInfo(this,"","",cbRememberPassword.isChecked(),cbAutomatic.isChecked());
					if(result){
						Log.i(tag,"登陆时不需要保存登录偏好，清除登录偏好信息成功...");
					}else{
						Log.i(tag,"登陆时不需要保存登录偏好，清除登录偏好信息失败...");
					}
				}
			}else{
				Toast.makeText(this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
			}
			pgBar.setVisibility(View.INVISIBLE);
		}
	}
	
	/**
	 * checkBox的选中状态改变事件
	 * 
	 * @param buttonView 即checkBox对象
	 * @param isCheck 操作后的checkBox的是否被选中状态
	 * @return void
	 */
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// 如果是"自动登录"复选框变更事件
		if(R.id.login_cb_automatic==buttonView.getId()){
			// 如果状态变更为选中
			if(isChecked){
				cbRememberPassword.setChecked(true);
			}
		}
		// 如果是"记住密码"复选框变更事件
		if(R.id.login_cb_remember_password==buttonView.getId()){
			// 如果状态变更为选中
			if(!isChecked){
				cbAutomatic.setChecked(false);
			}
		}
	}
}
