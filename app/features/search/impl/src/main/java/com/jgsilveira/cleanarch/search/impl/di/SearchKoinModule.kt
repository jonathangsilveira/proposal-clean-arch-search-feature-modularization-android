package com.jgsilveira.cleanarch.search.impl.di

import com.jgsilveira.cleanarch.search.android.navigation.SearchNavigation
import com.jgsilveira.cleanarch.search.impl.data.provider.FixedDeviceLocationProvider
import com.jgsilveira.cleanarch.search.impl.data.provider.InMemoryTrackerSessionIdProvider
import com.jgsilveira.cleanarch.search.impl.data.repository.InMemorySearchRepository
import com.jgsilveira.cleanarch.search.impl.data.repository.InMemoryUserPermissionRepository
import com.jgsilveira.cleanarch.search.impl.data.search.SearchParamsFactoryImpl
import com.jgsilveira.cleanarch.search.impl.domain.mapper.SearchContextConfigFactoryImpl
import com.jgsilveira.cleanarch.search.impl.domain.search.Searcher
import com.jgsilveira.cleanarch.search.impl.presentation.SearchUseCaseProvider
import com.jgsilveira.cleanarch.search.impl.presentation.SearchViewModel
import com.jgsilveira.cleanarch.search.impl.usecase.SearchUseCase
import com.jgsilveira.cleanarch.search.impl.usecase.UpdateReadContactsPermissionUseCase
import com.jgsilveira.cleanarch.search.impl.view.navigation.SearchNavigator
import com.jgsilveira.cleanarch.search.impl.domain.model.config.SearchContextConfig
import com.jgsilveira.cleanarch.search.impl.domain.search.KoinSearcherFactory
import com.jgsilveira.cleanarch.search.searchable.Searchable
import com.jgsilveira.cleanarch.search.factory.SearcherFactory
import org.koin.core.module.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.bind
import org.koin.dsl.module

val searchKoinModule = module {

    factory<SearchNavigation> {
        SearchNavigator(
            contextConfigFactory = SearchContextConfigFactoryImpl
        )
    }

    factory { (contextConfig: SearchContextConfig) ->
        Searcher(
            repository = InMemorySearchRepository(),
            paramsFactory = SearchParamsFactoryImpl(
                contextConfig = contextConfig,
                deviceLocationProvider = FixedDeviceLocationProvider,
                trackerSessionIdProvider = InMemoryTrackerSessionIdProvider()
            )
        )
    } bind Searchable::class

    factory<SearcherFactory> {
        KoinSearcherFactory(
            contextConfigFactory = SearchContextConfigFactoryImpl
        )
    }

    viewModel<SearchViewModel> { (contextConfig: SearchContextConfig) ->
        SearchViewModel(
            contextConfig = contextConfig,
            useCaseProvider = SearchUseCaseProvider(
                search = SearchUseCase(
                    searcher = get { parametersOf(contextConfig) }
                ),
                updateReadContactsPermission = UpdateReadContactsPermissionUseCase(
                    repository = InMemoryUserPermissionRepository
                )
            )
        )
    }
}