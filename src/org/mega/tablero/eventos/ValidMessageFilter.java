package org.mega.tablero.eventos;

import net.engio.mbassy.listener.IMessageFilter;
import net.engio.mbassy.listener.MessageHandler;

public class ValidMessageFilter implements IMessageFilter<Evento> {
    @Override
    public boolean accepts(Evento message, MessageHandler metadata) {
	return message.esValido();
    }

}
