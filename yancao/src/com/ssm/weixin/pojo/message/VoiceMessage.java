package com.ssm.weixin.pojo.message;


public class VoiceMessage extends BaseMessage{
	//语音
		  private Voice Voice;
		
		public Voice getVoice() {
			return Voice;
		}

		public void setVoice(Voice voice) {
			Voice = voice;
		}
		 
}
