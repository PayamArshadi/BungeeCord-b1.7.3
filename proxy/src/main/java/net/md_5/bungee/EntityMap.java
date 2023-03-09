package net.md_5.bungee;

import io.netty.buffer.ByteBuf;

/**
 * Class to rewrite integers within packets.
 */
public class EntityMap
{

    public final static int[][] entityIds = new int[ 256 ][];

    static
    {
        entityIds[0x05] = new int[]
        {
            1
        };
        entityIds[0x07] = new int[]
        {
            1, 5
        };
        entityIds[0x11] = new int[]
        {
            1
        };
        entityIds[0x12] = new int[]
        {
            1
        };
        entityIds[0x13] = new int[]
        {
            1
        };
        entityIds[0x14] = new int[]
        {
            1
        };
        entityIds[0x16] = new int[]
        {
            1, 5
        };
        entityIds[0x17] = new int[]
        {
            1 //, 20
        };
        entityIds[0x18] = new int[]
        {
            1
        };
        entityIds[0x19] = new int[]
        {
            1
        };
        entityIds[0x1A] = new int[]
        {
            1
        };
        entityIds[0x1C] = new int[]
        {
            1
        };
        entityIds[0x1E] = new int[]
        {
            1
        };
        entityIds[0x1F] = new int[]
        {
            1
        };
        entityIds[0x20] = new int[]
        {
            1
        };
        entityIds[0x21] = new int[]
        {
            1
        };
        entityIds[0x22] = new int[]
        {
            1
        };
        entityIds[0x23] = new int[]
        {
            1
        };
        entityIds[0x26] = new int[]
        {
            1
        };
        entityIds[0x27] = new int[]
        {
            1, 5
        };
        entityIds[0x28] = new int[]
        {
            1
        };
        entityIds[0x29] = new int[]
        {
            1
        };
        entityIds[0x2A] = new int[]
        {
            1
        };
        entityIds[0x2C] = new int[]
        {
            1
        };
        entityIds[0x37] = new int[]
        {
            1
        };

        entityIds[0x47] = new int[]
        {
            1
        };
    }

    public static void rewrite(ByteBuf packet, int oldId, int newId)
    {
        int packetId = packet.getUnsignedByte( 0 );
        if ( packetId == 0x1D )
        { // destroy entity
            int readId = packet.getInt( 1 );
            if ( readId == oldId )
            {
                packet.setInt( 1, newId );
            } else if ( readId == newId )
            {
                packet.setInt( 1, oldId );
            }
        } else
        {
            int[] idArray = entityIds[packetId];
            if ( idArray != null )
            {
                for ( int pos : idArray )
                {
                    int readId = packet.getInt( pos );
                    if ( readId == oldId )
                    {
                        packet.setInt( pos, newId );
                    } else if ( readId == newId )
                    {
                        packet.setInt( pos, oldId );
                    }
                }
            }
        }
        if ( packetId == 0x17 )
        {
            int type = packet.getUnsignedByte( 5 );
            if ( type == 60 || type == 90 )
            {
                int index20 = packet.getInt( 1 );
                if ( packet.readableBytes() > 5 && index20 == oldId )
                {
                    packet.setInt( 1, newId );
                }
            }
        }
    }
}
