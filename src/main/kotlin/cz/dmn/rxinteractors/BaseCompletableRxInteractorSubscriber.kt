package cz.dmn.rxinteractors

import io.reactivex.observers.DisposableCompletableObserver

abstract class BaseCompletableRxInteractorSubscriber : DisposableCompletableObserver() {
    override fun onComplete() {}
    override fun onError(error: Throwable) {}
}