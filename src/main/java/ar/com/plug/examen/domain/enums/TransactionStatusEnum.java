package ar.com.plug.examen.domain.enums;

public enum TransactionStatusEnum
{
    APPROVED( 1 ),
    REJECTED( 2 ),
    PENDING( 3 );

    private final long value;

    TransactionStatusEnum( long value )
    {
        this.value = value;
    }

    public long getValue()
    {
        return value;
    }
}
