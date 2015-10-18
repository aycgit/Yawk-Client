package me.riverhouse.candy.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class ChatMessage {

	/**
	 * @Auther: czaarek99
	 */

	/** The chatcomponent that will actually be passed to the minecraft code */
	private final ChatComponentText message;

	/** A private constructor so the class can only be created using the builder class */
	private ChatMessage(ChatComponentText message){
		this.message = message;
	}


	/** Public getter to get the chat component, currently only used by the defaultPrefix */
	public ChatComponentText getChatComponent(){
		return message;
	}

	/** The actual builder class */
	public static class ChatMessageBuilder {
		/** Constant holding the default message color */
		private static final EnumChatFormatting defaultMessageColor = EnumChatFormatting.AQUA;
		/** Constant holding the default prefix for a message */
		private static final ChatComponentText defaultPrefix = 
			new ChatMessage.ChatMessageBuilder()
			.appendText("Skittles").setColor(EnumChatFormatting.RED).bold()
			.appendText(" : ").setColor(EnumChatFormatting.DARK_GRAY).bold()
			.build()
			.getChatComponent();

		/** The message that will be returned when you call build()
		 * All styled text is appended to it while it is being built
		 * */
		private ChatComponentText theMessage = new ChatComponentText("");

		/** A boolean that indicates whatever the default message color
		 * should be applied to each message, note that you can still override
		 * the default color with the setColor() if this is true
		 * */
		private boolean useDefaultMessageColor = false;

		/** Our 2 working fields that hold the part of the message
		 * that is being constructed
		 *
		 * Every time you call appendText() the data from them
		 * gets moved to "theMessage" and they get reset
		 * */
		private ChatStyle workingStyle = new ChatStyle();
		private ChatComponentText workerMessage = new ChatComponentText("");

		/**
		 * The constructor if you want to use any of the defaults
		 *
		 * @param prependDefaultPrefix A boolean indicating whetever the default prefix
		 *                             should be prepended to this message
		 * @param useDefaultMessageColor A boolean indicating whatever the default message
		 *                               color should be applied to this message
		 */
		public ChatMessageBuilder(boolean prependDefaultPrefix){
			if(prependDefaultPrefix) theMessage.appendSibling(defaultPrefix);
		}

		/**
		 * Just an empty constructor if defaults will not be used
		 */
		public ChatMessageBuilder(){

		}

		public ChatMessageBuilder appendText(String text){
			appendSibling();
			workerMessage = new ChatComponentText(text);
			workingStyle = new ChatStyle();
			if(useDefaultMessageColor){
				setColor(defaultMessageColor);
			}
			return this;
		}
		/** Sets the color of the current text */
		public ChatMessageBuilder setColor(EnumChatFormatting color){
			workingStyle.setColor(color);
			return this;
		}
		/** Makes the current text bold*/
		public ChatMessageBuilder bold(){
			workingStyle.setBold(true);
			return this;
		}

		/** Makes the current text italic */
		public ChatMessageBuilder italic(){
			workingStyle.setItalic(true);
			return this;
		}

		/** Applies the strikethrough effect to the current text */
		public ChatMessageBuilder strikethrough(){
			workingStyle.setStrikethrough(true);
			return this;
		}

		/** Adds an underline to the current text */
		public ChatMessageBuilder underline(){
			workingStyle.setUnderlined(true);
			return this;
		}

		/** Builds the actual ChatMessage object */
		public ChatMessage build(){
			appendSibling();
			return new ChatMessage(theMessage);
		}

		/** A method that adds the current text that is being modified to
		 * our final message
		 * */
		private void appendSibling(){
			theMessage.appendSibling(workerMessage.setChatStyle(workingStyle));
		}

	}
}
