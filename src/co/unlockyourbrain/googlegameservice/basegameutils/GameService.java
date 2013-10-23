package co.unlockyourbrain.googlegameservice.basegameutils;

import com.google.android.gms.appstate.AppStateClient;
import com.google.android.gms.games.GamesClient;
import com.google.android.gms.plus.PlusClient;

import android.app.Activity;
import android.content.Intent;

/**
 * This class is similar to the BaseGameActivity. For reference please read that class.
 * Created by john on 16/10/13.
 */
public abstract class GameService implements GameHelper.GameHelperListener {
	
	public static final int CLIENT_GAMES = GameHelper.CLIENT_GAMES;
    public static final int CLIENT_APPSTATE = GameHelper.CLIENT_APPSTATE;
    public static final int CLIENT_PLUS = GameHelper.CLIENT_PLUS;
    public static final int CLIENT_ALL = GameHelper.CLIENT_ALL;
	
	private GameHelper mHelper;
	private Activity activity;
	private boolean mDebugLog = true;
	protected String mDebugTag = "GameService";
	protected int mRequestedClients = GameHelper.CLIENT_GAMES;
	private String[] mAdditionalScopes;
	
	public GameService(Activity activity){
		this.activity = activity;
		mHelper = new GameHelper(activity);
	}
	
	public GameService(Activity activity, int requestedClients){
		this.activity = activity;
		setRequestedClients(requestedClients);
	}
	
    protected void setRequestedClients(int requestedClients, String ... additionalScopes) {
        mRequestedClients = requestedClients;
        mAdditionalScopes = additionalScopes;
    }
	
	public void onCreate(){
		mHelper = new GameHelper(activity);
        if (mDebugLog) {
            mHelper.enableDebugLog(mDebugLog, mDebugTag);
        }
        mHelper.setup(this, mRequestedClients, mAdditionalScopes);
	}
	
	public void onStart(){
		mHelper.onStart(activity);
	}
	
	public void onStop(){
		mHelper.onStop();
	}
	
	public void onResult(int request, int response, Intent data){
		mHelper.onActivityResult(request, response, data);
	}
	
	public GamesClient getGameClient(){
		return mHelper.getGamesClient();
	}

    public AppStateClient getAppStateClient() {
        return mHelper.getAppStateClient();
    }

    public PlusClient getPlusClient() {
        return mHelper.getPlusClient();
    }

    public boolean isSignedIn() {
        return mHelper.isSignedIn();
    }

    public void beginUserInitiatedSignIn() {
        mHelper.beginUserInitiatedSignIn();
    }

    public void signOut() {
        mHelper.signOut();
    }

    public void showAlert(String title, String message) {
        mHelper.showAlert(title, message);
    }

    public void showAlert(String message) {
        mHelper.showAlert(message);
    }

    public void enableDebugLog(boolean enabled, String tag) {
        mDebugLog = true;
        mDebugTag = tag;
        if (mHelper != null) {
            mHelper.enableDebugLog(enabled, tag);
        }
    }

    public String getInvitationId() {
        return mHelper.getInvitationId();
    }

    public void reconnectClients(int whichClients) {
        mHelper.reconnectClients(whichClients);
    }

    public String getScopes() {
        return mHelper.getScopes();
    }

    public String[] getScopesArray() {
        return mHelper.getScopesArray();
    }

    public boolean hasSignInError() {
        return mHelper.hasSignInError();
    }

    public GameHelper.SignInFailureReason getSignInError() {
        return mHelper.getSignInError();
    }

}
