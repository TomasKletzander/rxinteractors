package cz.dmn.rxinteractors

open class BaseSingleRxInteractorCallbackSubscriber<T>(
    private val callbackSuccess: ((T) -> Unit)?,
    private val callbackError: ((Throwable) -> Unit)?
) : BaseSingleRxInteractorSubscriber<T>() {

    override fun onSuccess(response: T) {
        callbackSuccess?.invoke(response)
    }

    override fun onError(e: Throwable) {
        callbackError?.invoke(e)
    }
}