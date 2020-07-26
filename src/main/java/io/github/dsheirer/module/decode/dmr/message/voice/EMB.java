/*
 * *****************************************************************************
 *  Copyright (C) 2014-2020 Dennis Sheirer
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 * ****************************************************************************
 */

package io.github.dsheirer.module.decode.dmr.message.voice;

import io.github.dsheirer.bits.CorrectedBinaryMessage;
import io.github.dsheirer.module.decode.dmr.message.type.LCSS;

/**
 * DMR Voice Frame B-F Embedded Signalling Chunk
 */
public class EMB
{
    private static final int[] COLOR_CODE = new int[]{0, 1, 2, 3};
    private static final int ENCRYPTION_PI = 4;
    private static final int[] LINK_CONTROL_START_STOP = new int[]{5, 6};

    private CorrectedBinaryMessage mMessage;
    private boolean mValid = true;

    /**
     * Constructs an instance
     * @param message with transmitted bits
     */
    public EMB(CorrectedBinaryMessage message)
    {
        mMessage = message;
        //TODO: crc check
    }

    /**
     * Indicates if this message is valid and has passed CRC check
     */
    public boolean isValid()
    {
        return mValid;
    }

    /**
     * Message bits
     */
    protected CorrectedBinaryMessage getMessage()
    {
        return mMessage;
    }

    /**
     * Color code
     */
    public int getColorCode()
    {
        return getMessage().getInt(COLOR_CODE);
    }

    /**
     * Link Control Start-Stop
     */
    public LCSS getLCSS()
    {
        return LCSS.fromValue(getMessage().getInt(LINK_CONTROL_START_STOP));
    }

    /**
     * Indicates if this call is encrypted
     */
    public boolean isEncrypted()
    {
        return getMessage().get(ENCRYPTION_PI);
    }
}