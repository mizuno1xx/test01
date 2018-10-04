package texttoSpeech;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.SynthesizeOptions;
import com.ibm.watson.developer_cloud.text_to_speech.v1.util.WaveUtils;

public class TexttoSpeech 
{
	public static void main(String[] args) 
	{
		TextToSpeech synthesizer = new TextToSpeech();
		synthesizer.setUsernameAndPassword("1618101", "1618101");
		
		String translation = "¡“ú‚Í—Ç‚¢“V‹C‚Å‚·‚ËB";
		
		SynthesizeOptions synthesizeOptions = new SynthesizeOptions.Builder()
			.text(translation)
			.voice(SynthesizeOptions.Voice.JA_JP_EMIVOICE)
			.accept(SynthesizeOptions.Accept.AUDIO_WAV)
			.build();
		InputStream in = synthesizer.synthesize(synthesizeOptions).execute();
		
		try 
		{
			writeToFile(WaveUtils.reWriteWaveHeader(in), new File("output.wav"));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	private static void writeToFile(InputStream in, File file) 
	{
		try 
		{
		   OutputStream out = new FileOutputStream(file);
		   byte[] buf = new byte[1024];
		   int len;
		   while ((len = in.read(buf)) > 0) 
		   {
			   out.write(buf, 0, len);
		   }
		   out.close();
		   in.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}

