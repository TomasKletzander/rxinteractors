package cz.dmn.rxinteractors

import io.reactivex.observers.DisposableSingleObserver

abstract class BaseSingleRxInteractorSubscriber<T> : DisposableSingleObserver<T>() {

    override fun onSuccess(response: T) {}
    override fun onError(e: Throwable) {}
}