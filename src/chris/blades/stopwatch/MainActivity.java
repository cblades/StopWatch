package chris.blades.stopwatch;

import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends Activity implements View.OnClickListener {

	private Button startButton;
	
	private Button resetButton;
	
	private Chronometer chrono;
	
	private boolean started = false;
	
	private long elapsedTime = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		startButton = (Button)this.findViewById(R.id.start_button);
		
		resetButton = (Button)this.findViewById(R.id.reset_button);
		
		chrono = (Chronometer)this.findViewById(R.id.chrono);
		
		startButton.setOnClickListener(this);
		resetButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View clickedView) {
		if (clickedView.equals(startButton)) {
			if (!started) {
				if (elapsedTime > 0) {
					chrono.setBase(SystemClock.elapsedRealtime() - elapsedTime);
				}
				chrono.start();
				
				startButton.setText(R.string.stop);
				started = true;
			} else {
				chrono.stop();
				elapsedTime = SystemClock.elapsedRealtime() - chrono.getBase();
				startButton.setText(R.string.start);
				started = false;
			}			
		} else {
			chrono.setBase(SystemClock.elapsedRealtime());
			elapsedTime = 0;
		}
	}
}
