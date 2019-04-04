package talitha_koum.milipade.com.app.vhuka.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.util.PatternsCompat;
import androidx.fragment.app.Fragment;

import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.sapereaude.maskedEditText.MaskedEditText;
import talitha_koum.milipade.com.app.vhuka.ImageActivity;
import talitha_koum.milipade.com.app.vhuka.MainActivity;
import talitha_koum.milipade.com.app.vhuka.R;
import talitha_koum.milipade.com.app.vhuka.utils.CustomToast;
import talitha_koum.milipade.com.app.vhuka.utils.Utils;

public class Signup_fragment extends Fragment implements OnClickListener {

	private static View view;
	MaskedEditText mobileNumber;
	private static EditText fullName, emailId,  age;
	private static TextView login;
	private static Button signUpButton;
	private static CheckBox terms_conditions;
	private static Animation shakeAnimation;
	private static LinearLayout loginLayout;
	private final static String TAG = Signup_fragment.class.getSimpleName();
	ProgressDialog progressDialog;


	public Signup_fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.signup_layout, container, false);
		progressDialog = new ProgressDialog(getActivity());
		initViews();
		setListeners();
		return view;
	}

	// Initialize all views
	private void initViews() {
		fullName = (EditText) view.findViewById(R.id.fullName);
		emailId = (EditText) view.findViewById(R.id.userEmailId);
		mobileNumber = (MaskedEditText) view.findViewById(R.id.mobileNumber);
		age = (EditText) view.findViewById(R.id.age);

		signUpButton = (Button) view.findViewById(R.id.signUpBtn);
		login = (TextView) view.findViewById(R.id.already_user);
		terms_conditions = (CheckBox) view.findViewById(R.id.terms_conditions);

		loginLayout = (LinearLayout) view.findViewById(R.id.signup_layout);
		// Load ShakeAnimation
		shakeAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
	}

	// Set Listeners
	private void setListeners() {
		signUpButton.setOnClickListener(this);
		login.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.signUpBtn:

				// Call checkValidation method
				checkValidation();
				break;

			case R.id.already_user:

				// Replace login fragment
				new MainActivity().replaceLoginFragment();
				break;
		}

	}

	// Check Validation Method
	private void checkValidation() {

		// Get all edittext texts
		String getFullName = fullName.getText().toString();
		String getEmailId = emailId.getText().toString();
		String getMobileNumber = mobileNumber.getText().toString();
		String getAge = age.getText().toString();

		// Pattern match for email id
		Pattern e = Pattern.compile(Utils.regEx);
		Matcher m = e.matcher(getEmailId);

		Pattern s = Pattern.compile(Utils.regExCell);
		Matcher g = s.matcher(getMobileNumber);

		Pattern u = Pattern.compile(Utils.regex);
		Matcher U = u.matcher(getMobileNumber);

		// Check if all strings are null or not
		if (getFullName.equals("") || getFullName.length() == 0
				|| getEmailId.equals("") || getEmailId.length() == 0
				|| getMobileNumber.equals("") || getMobileNumber.length() == 0
				|| getAge.equals("") || getAge.length() == 0

		) {

			loginLayout.startAnimation(shakeAnimation);
			new CustomToast().Show_Toast(getActivity(), view, "All fields are required.");

			// Check if email id valid or not !m.find() || Patterns.WEB_URL.matcher(linkUrl).matches()
		} else if (PatternsCompat.EMAIL_ADDRESS.matcher(getEmailId).matches() ==false & PatternsCompat.WEB_URL.matcher(getEmailId).matches()==false) {
			loginLayout.startAnimation(shakeAnimation);
			new CustomToast().Show_Toast(getActivity(), view, m.find()+"Email or URl  is Invalid." +PatternsCompat.WEB_URL.matcher(getEmailId).matches());

		} else if (!g.find() ) {
			loginLayout.startAnimation(shakeAnimation);

			new CustomToast().Show_Toast(getActivity(), view, "Cell Number is Invalid." );

		} else if (Integer.parseInt(getAge) <= 17) {
			loginLayout.startAnimation(shakeAnimation);
			new CustomToast().Show_Toast(getActivity(), view, "You have to be 18 years and above ");

			// Make sure user should check Terms and Conditions checkbox
		} else if (!terms_conditions.isChecked()) {
			loginLayout.startAnimation(shakeAnimation);

		new CustomToast().Show_Toast(getActivity(), view, "Please select Terms and Conditions.");


	}else{

		createUser(getFullName, getEmailId, getMobileNumber, getAge);
	}
}

	private void createUser(final String getFullName, final String getEmailId, final String getMobileNumber, final String getAge) {

		progressDialog.setMessage("Please Wait, We are Registering You");
		progressDialog.show();
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				Intent intent = new Intent(getActivity(), ImageActivity.class);
				intent.putExtra("AGE", getAge);
				startActivity(intent);
			}
		}, 2000);

	}
}
