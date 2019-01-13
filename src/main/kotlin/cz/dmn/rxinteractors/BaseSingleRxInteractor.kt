package cz.dmn.rxinteractors

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.SingleTransformer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseSingleRxInteractor<A: Any?, D>(
    private val disposables: CompositeDisposable,
    private val subscribeScheduler: Scheduler,
    private val observeScheduler: Scheduler
) {

    fun <O> execute(argument: A, disposableObserver: O) where O: Disposable, O: SingleObserver<D> {
        disposables.add(buildInteractorObservable(argument)
            .compose(applySchedulersSingle())
            .subscribeWith(disposableObserver))
    }

    fun unsubscribe() {
        disposables.clear()
    }

    private fun <D> applySchedulersSingle(): SingleTransformer<D, D> {
        return SingleTransformer { upstream ->
            upstream
                .subscribeOn(subscribeScheduler)
                .unsubscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
        }
    }

    protected abstract fun buildInteractorObservable(argument: A): Single<D>
}