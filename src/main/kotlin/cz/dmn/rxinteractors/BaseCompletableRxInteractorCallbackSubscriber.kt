package cz.dmn.rxinteractors

open class BaseCompletableRxInteractorCallbackSubscriber(
    private val callbackSuccess: (() -> Unit)?,
    private val callbackError: ((Throwable) -> Unit)?
) : BaseCompletableRxInteractorSubscriber() {

    override fun onComplete() {
        callbackSuccess?.invoke()
    }

    override fun onError(error: Throwable) {
        callbackError?.invoke(error)
    }
}